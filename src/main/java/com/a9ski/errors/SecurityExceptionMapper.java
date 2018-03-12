package com.a9ski.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {

	@Override
	public Response toResponse(final SecurityException exception) {
		return Response.status(Response.Status.FORBIDDEN).build();
	}

}
