package com.a9ski.ws;

import java.util.Collections;
import java.util.Set;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.a9ski.entities.filters.Filter;
import com.a9ski.id.MutableAuditable;
import com.a9ski.security.Permission;
import com.a9ski.security.PermissionInterceptor;
import com.a9ski.security.UserPrincipal;
import com.a9ski.utils.VersionUtils;

@Interceptors({ PermissionInterceptor.class })
public abstract class AbstractService {

	@Inject
	protected Instance<UserPrincipal> userPrincipalProvider;

	@javax.ws.rs.Path("version")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String version() {
		return getClass().getSimpleName() + "." + VersionUtils.getVersion() + "@" + VersionUtils.getBuildTime() + "/" + VersionUtils.getBuildId();
	}

	protected <F extends Filter> F getFilter(final F filter, final Class<F> filterClass) {
		if (filter == null) {
			try {
				return filterClass.newInstance();
			} catch (final InstantiationException | IllegalAccessException ex) {
				throw new RuntimeException("Cannot create instance of filter class:" + filterClass.getName(), ex);

			}
		} else {
			return filter;
		}
	}

	public UserPrincipal getUserPrincipal() {
		return userPrincipalProvider.get();
	}

	public Set<Permission> getUserPermissions() {
		final UserPrincipal principal = getUserPrincipal();
		return principal != null ? principal.getPermissions() : Collections.emptySet();
	}

	public void touch(MutableAuditable e) {
		final UserPrincipal p = getUserPrincipal();
		if (p != null) {
			e.touch(p.getId());
		}
	}
}
