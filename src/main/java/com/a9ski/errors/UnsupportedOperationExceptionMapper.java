package com.a9ski.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnsupportedOperationExceptionMapper implements ExceptionMapper<UnsupportedOperationException> {

	@Override
	public Response toResponse(final UnsupportedOperationException exception) {
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}

}
