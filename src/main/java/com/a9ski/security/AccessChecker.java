package com.a9ski.security;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.CollectionUtils;

import com.a9ski.utils.ExtCollectionUtils;

@FunctionalInterface
public interface AccessChecker {
	public boolean hasAccess(Set<Permission> permissions);

	public default boolean hasAccess(Permission permission) {
		return permission != null && hasAccess(ExtCollectionUtils.toSet(permission));
	}

	public static final AccessChecker PERMIT_ALL = (permissions -> true);
	public static final AccessChecker DENY_ALL = (permissions -> false);

	public static AccessChecker permit(Permission... permitPermissions) {
		final Set<Permission> permitPermissionsSet = ExtCollectionUtils.collection(TreeSet::new, permitPermissions);
		return (userPermissions -> CollectionUtils.containsAny(permitPermissionsSet, userPermissions));
	}

	public static AccessChecker deny(Permission... denyPermissions) {
		final Set<Permission> denyPermissionsSet = ExtCollectionUtils.collection(TreeSet::new, denyPermissions);
		return (userPermissions -> !CollectionUtils.containsAny(denyPermissionsSet, userPermissions));
	}
}
