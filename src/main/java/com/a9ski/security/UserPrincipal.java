package com.a9ski.security;

import java.util.Locale;
import java.util.Set;

import com.a9ski.id.Identifiable;
import com.a9ski.id.LoginBased;
import com.a9ski.utils.TimeZoneList.NamedTimeZone;

public class UserPrincipal implements Identifiable, LoginBased {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5247452631327806804L;

	private final long id;
	private final String login;
	private final Set<Permission> permissions;
	private final Locale locale;
	private final NamedTimeZone timeZone;

	public UserPrincipal(final long id, final String login, final Set<Permission> permissions, final Locale locale, final NamedTimeZone timeZone) {
		super();
		this.id = id;
		this.login = login;
		this.permissions = permissions;
		this.locale = locale;
		this.timeZone = timeZone;
	}

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public Locale getLocale() {
		return locale;
	}

	public NamedTimeZone getTimeZone() {
		return timeZone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrincipal other = (UserPrincipal) obj;
		if (id != other.id)
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (permissions == null) {
			if (other.permissions != null)
				return false;
		} else if (!permissions.equals(other.permissions))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}
}
