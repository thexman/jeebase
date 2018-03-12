package com.a9ski.security;

import java.util.Arrays;
import java.util.Collection;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PermissionInterceptor {

	private static final Permission[] emptyArray = new Permission[0];

	@Inject
	private Instance<UserPrincipal> userPrincipalProvider;

	private boolean hasPermission(final Collection<Permission> permissions, final Permission p) {
		return permissions != null && permissions.contains(p);
	}

	private boolean hasAnyPermissions(final Collection<Permission> userPermissions, final Permission[] permissions) {
		return permissions != null && Arrays.stream(permissions).filter(p -> hasPermission(userPermissions, p)).findFirst().isPresent();
	}

	private String getMethodName(final InvocationContext ctx) {
		final String className = ctx.getMethod().getDeclaringClass().getName();
		final String method = ctx.getMethod().getName();
		return className + "." + method;
	}

	@AroundInvoke
	public Object checkPermissions(final InvocationContext ctx) throws Exception {
		if (userPrincipalProvider != null) {
			final UserPrincipal principal = userPrincipalProvider.get();
			if (principal != null) {
				checkAccessAnotation(ctx, principal);

				checkPermitAnnotation(ctx, principal);

				checkDenyAnnotation(ctx, principal);
			}
		}

		return ctx.proceed();
	}

	private void checkAccessAnotation(final InvocationContext ctx, final UserPrincipal principal) {
		final Access access = ctx.getMethod().getAnnotation(Access.class);
		if (access != null && access.permit() != null && !hasAnyPermissions(principal.getPermissions(), access.permit())) {
			throwSecurityException(ctx, access);
		}
		if (access != null && access.deny() != null && hasAnyPermissions(principal.getPermissions(), access.deny())) {
			throwSecurityException(ctx, access);
		}
	}

	private void checkDenyAnnotation(final InvocationContext ctx, final UserPrincipal principal) {
		final Permit deny = ctx.getMethod().getAnnotation(Permit.class);
		if (deny != null && deny.value() != null && hasAnyPermissions(principal.getPermissions(), deny.value())) {
			throwSecurityException(ctx, deny);
		}
	}

	private void checkPermitAnnotation(final InvocationContext ctx, final UserPrincipal principal) {
		final Permit permit = ctx.getMethod().getAnnotation(Permit.class);
		if (permit != null && permit.value() != null && !hasAnyPermissions(principal.getPermissions(), permit.value())) {
			throwSecurityException(ctx, permit);
		}
	}

	protected void throwSecurityException(final String msg) {
		throw new SecurityException(msg);
	}

	protected void throwSecurityException(final InvocationContext ctx, final Permit annotation) {
		final Permission[] permissions = annotation != null && annotation.value() != null ? annotation.value() : emptyArray;
		final String msg = String.format("Access denied. Method '%s' requires permission(s): %s", getMethodName(ctx), Arrays.asList(permissions));
		throwSecurityException(msg);
	}

	protected void throwSecurityException(final InvocationContext ctx, final Deny annotation) {
		final Permission[] permissions = annotation != null && annotation.value() != null ? annotation.value() : emptyArray;
		final String msg = String.format("Access denied. Method '%s' explicitly denied for permission(s): %s", getMethodName(ctx), Arrays.asList(permissions));
		throwSecurityException(msg);
	}

	protected void throwSecurityException(final InvocationContext ctx, final Access annotation) {
		final Permission[] permit = annotation != null && annotation.permit() != null ? annotation.permit() : emptyArray;
		final Permission[] deny = annotation != null && annotation.deny() != null ? annotation.deny() : emptyArray;
		final String msg = String.format("Access denied. Method '%s' requires permission(s): %s and denies permission(s): %s", getMethodName(ctx), Arrays.asList(permit), Arrays.asList(deny));
		throwSecurityException(msg);
	}

}
