package com.a9ski.entities;

import com.a9ski.entities.filters.AuditableEntityFilter;
import com.a9ski.entities.filters.FilterStringField;

public class RoleFilter extends AuditableEntityFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7923304604349854963L;

	private FilterStringField name;

	public RoleFilter() {
		super();
	}

	public FilterStringField getName() {
		return name;
	}

	public void setName(FilterStringField name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleFilter other = (RoleFilter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
