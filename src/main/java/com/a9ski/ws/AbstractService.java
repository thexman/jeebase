package com.a9ski.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.a9ski.entities.filters.IdentifiableEntityFilter;
import com.a9ski.utils.VersionUtils;

public abstract class AbstractService {
	@javax.ws.rs.Path("version")
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public String version() {
		return getClass().getSimpleName() + "." + VersionUtils.getVersion() + "@" + VersionUtils.getBuildTime() + "/" + VersionUtils.getBuildId();
	}

	protected <F extends IdentifiableEntityFilter> F getFilter(final F filter, final Class<F> filterClass) {
		if (filter == null) {
			try {
				return filterClass.newInstance();
			} catch (final InstantiationException | IllegalAccessException ex) {
				throw new RuntimeException("Cannot create instance of filter class:" + filterClass.getName(), ex);

			}
		} else {
			return filter;
		}
	}
}
