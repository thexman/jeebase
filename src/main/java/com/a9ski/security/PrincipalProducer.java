package com.a9ski.security;

import java.security.Principal;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.ObjectUtils;

import com.a9ski.entities.Role;
import com.a9ski.entities.User;
import com.a9ski.utils.ExtCollectionUtils;
import com.a9ski.utils.TimeZoneList;
import com.a9ski.utils.TimeZoneList.NamedTimeZone;

@RequestScoped
public class PrincipalProducer {
	@PersistenceContext(unitName = "jeeBase")
	private EntityManager em;

	@Inject
	private Principal principal;

	@Context
	private HttpServletRequest request;

	@Produces
	public UserPrincipal loadPrincipal() {
		final TypedQuery<User> q = em.createNamedQuery(User.NAMED_QUERY_FIND_BY_LOGIN, User.class);
		q.setParameter("login", principal.getName());
		final User user = ExtCollectionUtils.get0(q.getResultList());
		if (user != null) {
			final Set<Permission> permissions = new TreeSet<>();
			for (final Role r : user.getRoles()) {
				permissions.addAll(r.getPermissions());
			}

			final Locale locale = ObjectUtils.defaultIfNull(user.getLocale(), Locale.getDefault());
			final NamedTimeZone timeZone = ObjectUtils.defaultIfNull(user.getTimeZone(), TimeZoneList.getInstance().getDefaultTimeZone());
			return new UserPrincipal(user.getId(), user.getLogin(), permissions, locale, timeZone);
		}
		return null;
	}
}
