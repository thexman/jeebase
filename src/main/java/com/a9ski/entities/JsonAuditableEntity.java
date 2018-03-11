package com.a9ski.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "new", "existing", "notDeleted" })
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class JsonAuditableEntity extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1288992551958643450L;

}
