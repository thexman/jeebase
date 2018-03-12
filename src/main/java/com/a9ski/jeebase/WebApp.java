package com.a9ski.jeebase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.a9ski.errors.ObjectAlreadyModifiedMapper;
import com.a9ski.json.JsonParamConverter;
import com.a9ski.utils.ExtCollectionUtils;
import com.a9ski.ws.PermissionService;
import com.a9ski.ws.RoleService;
import com.a9ski.ws.UserService;

@ApplicationPath("/ws")
public class WebApp extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = ExtCollectionUtils.addAll(new HashSet<>(), super.getClasses(), null);
		classes.add(JacksonFeature.class);
		classes.add(ObjectAlreadyModifiedMapper.class);
		classes.add(UserService.class);
		classes.add(RoleService.class);
		classes.add(PermissionService.class);
		classes.add(JsonParamConverter.class);
		return classes;
	}

	@Override
	public Map<String, Object> getProperties() {
		final Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.disableMoxyJson", true);
		return properties;
	}
}
