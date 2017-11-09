package com.a9ski.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.a9ski.utils.VersionUtils;

public abstract class AbstractService {
	@javax.ws.rs.Path("version")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String version() {
		return getClass().getSimpleName() + "." + VersionUtils.getVersion() + "@" + VersionUtils.getBuildTime() + "/" + VersionUtils.getBuildId();
	}
}
