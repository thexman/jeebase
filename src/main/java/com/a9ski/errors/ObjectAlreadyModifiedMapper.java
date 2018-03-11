package com.a9ski.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.a9ski.exceptions.ObjectAlreadyModifiedException;

@Provider
public class ObjectAlreadyModifiedMapper implements ExceptionMapper<ObjectAlreadyModifiedException> {

	@Override
	public Response toResponse(final ObjectAlreadyModifiedException exception) {
		return Response.status(Response.Status.CONFLICT).build();
	}

}
