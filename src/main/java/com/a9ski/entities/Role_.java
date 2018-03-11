package com.a9ski.entities;

import com.a9ski.security.Permission;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-03-11T17:00:45.821+0200")
@StaticMetamodel(Role.class)
public class Role_ extends JsonAuditableEntity_ {
	public static volatile SingularAttribute<Role, String> name;
	public static volatile SetAttribute<Role, Permission> permissions;
}
