package com.a9ski.entities;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.a9ski.id.MutableNamed;
import com.a9ski.security.Permission;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "roles")
public class Role extends JsonAuditableEntity implements MutableNamed {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939528415584653159L;

	@Column(name = "name")
	private String name;

	@ElementCollection(targetClass = Permission.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "roleid"))
	@Column(name = "permission")
	private Set<Permission> permissions;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
