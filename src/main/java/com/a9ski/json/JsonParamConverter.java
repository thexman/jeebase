package com.a9ski.json;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

import com.a9ski.utils.TimeZoneList.NamedTimeZone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonParamConverter implements ParamConverterProvider {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		final SimpleModule module = new SimpleModule();
		module.addDeserializer(NamedTimeZone.class, new NamedTimeZoneDeserializer());
		MAPPER.registerModule(module);
	}

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		return new ParamConverter<T>() {

			@Override
			public T fromString(String value) {
				try {
					return (value != null ? MAPPER.readValue(value, rawType) : null);
				} catch (final IOException ex) {
					throw new RuntimeException(ex);
				}
			}

			@Override
			public String toString(T value) {
				try {
					return (value != null ? MAPPER.writeValueAsString(value) : null);
				} catch (final JsonProcessingException ex) {
					throw new RuntimeException(ex);
				}
			}
		};
	}

}
