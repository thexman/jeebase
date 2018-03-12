package com.a9ski.ws;

import java.util.EnumSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.a9ski.security.Permission;

@javax.ws.rs.Path("/permission")
@Stateless
@LocalBean
@Named
public class PermissionService extends AbstractService {

	@javax.ws.rs.Path("/list")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Set<Permission> list() {
		return EnumSet.allOf(Permission.class);
	}
}
