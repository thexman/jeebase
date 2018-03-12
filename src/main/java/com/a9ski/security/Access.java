package com.a9ski.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
	@Nonbinding
	Permission[] permit() default {};

	@Nonbinding
	Permission[] deny() default {};
}
