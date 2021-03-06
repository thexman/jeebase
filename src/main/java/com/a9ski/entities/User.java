package com.a9ski.entities;

import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.a9ski.id.MutableLoginBased;
import com.a9ski.jpa.LocaleConverter;
import com.a9ski.jpa.NamedTimeZoneConverter;
import com.a9ski.json.NamedTimeZoneDeserializer;
import com.a9ski.utils.TimeZoneList.NamedTimeZone;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//@formatter:off
@NamedQueries({
	@NamedQuery(name = User.NAMED_QUERY_FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = :login")
})

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
//@formatter:on
public class User extends JsonAuditableEntity implements MutableLoginBased {

	/**
	 *
	 */
	private static final long serialVersionUID = 3260478782599962812L;

	public static final String NAMED_QUERY_FIND_BY_LOGIN = "User.findByLogin";

	@Column(name = "login")
	private String login;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "locale")
	@Convert(converter = LocaleConverter.class)
	private Locale locale;

	@JsonDeserialize(using = NamedTimeZoneDeserializer.class)
	@Column(name = "timezone")
	@Convert(converter = NamedTimeZoneConverter.class)
	private NamedTimeZone timeZone;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	private List<Role> roles;

	/**
	 * @return the login
	 */
	@Override
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	@Override
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the timeZone
	 */
	public NamedTimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone
	 *            the timeZone to set
	 */
	public void setTimeZone(final NamedTimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		final User other = (User) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (locale == null) {
			if (other.locale != null) {
				return false;
			}
		} else if (!locale.equals(other.locale)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (timeZone == null) {
			if (other.timeZone != null) {
				return false;
			}
		} else if (!timeZone.equals(other.timeZone)) {
			return false;
		}
		return true;
	}

}
