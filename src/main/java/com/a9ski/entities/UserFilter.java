package com.a9ski.entities;

import javax.xml.bind.annotation.XmlElement;

import com.a9ski.entities.filters.AuditableEntityFilter;
import com.a9ski.entities.filters.FilterStringField;

//@XmlRootElement
public class UserFilter extends AuditableEntityFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6365195043097356213L;

	@XmlElement
	private FilterStringField login;

	@XmlElement
	private FilterStringField firstName;

	@XmlElement
	private FilterStringField lastName;

	@XmlElement
	private FilterStringField email;

	public UserFilter() {
		super();
	}

	/**
	 * @return the login
	 */

	public FilterStringField getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(FilterStringField login) {
		this.login = login;
	}

	/**
	 * @return the firstName
	 */
	public FilterStringField getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(FilterStringField firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public FilterStringField getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(FilterStringField lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public FilterStringField getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(FilterStringField email) {
		this.email = email;
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof UserFilter)) {
			return false;
		}
		UserFilter other = (UserFilter) obj;
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
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		return true;
	}
}
