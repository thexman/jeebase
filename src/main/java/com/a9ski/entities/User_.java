package com.a9ski.entities;

import java.util.Locale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-03-11T17:00:45.833+0200")
@StaticMetamodel(User.class)
public class User_ extends JsonAuditableEntity_ {
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Locale> locale;
	public static volatile ListAttribute<User, Role> roles;
}
