package com.a9ski.jeebase;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.a9ski.utils.ExtCollectionUtils;
import com.a9ski.ws.UserService;

@ApplicationPath("/ws")
public class WebApp extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = ExtCollectionUtils.addAll(new HashSet<>(), super.getClasses(), null);
		classes.add(UserService.class);
		return classes;
	}
}
