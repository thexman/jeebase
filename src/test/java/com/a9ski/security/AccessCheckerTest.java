package com.a9ski.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.a9ski.utils.ExtCollectionUtils;

public class AccessCheckerTest {

	@Test
	public void testPermit() {
		final AccessChecker c = AccessChecker.permit(Permission.LOGIN, Permission.LIST_USERS);
		assertTrue(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LOGIN, Permission.EDIT_USER)));
		assertTrue(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LIST_USERS, Permission.EDIT_USER)));
		assertFalse(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LIST_ROLES, Permission.EDIT_USER)));
	}

	@Test
	public void testDeny() {
		final AccessChecker c = AccessChecker.deny(Permission.LOGIN, Permission.LIST_USERS);
		assertFalse(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LOGIN, Permission.EDIT_USER)));
		assertFalse(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LIST_USERS, Permission.EDIT_USER)));
		assertTrue(c.hasAccess(ExtCollectionUtils.toSet(Permission.EDIT_ROLE, Permission.LIST_ROLES, Permission.EDIT_USER)));
	}

	@Test
	public void testPermitAll() {
		final AccessChecker c = AccessChecker.PERMIT_ALL;
		Arrays.stream(Permission.values()).forEach(p -> assertTrue(c.hasAccess(p)));
	}

	@Test
	public void testDenyAll() {
		final AccessChecker c = AccessChecker.DENY_ALL;
		Arrays.stream(Permission.values()).forEach(p -> assertFalse(c.hasAccess(p)));
	}

}
