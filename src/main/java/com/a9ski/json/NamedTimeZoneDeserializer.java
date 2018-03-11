package com.a9ski.json;

import java.io.IOException;

import com.a9ski.utils.TimeZoneList;
import com.a9ski.utils.TimeZoneList.NamedTimeZone;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class NamedTimeZoneDeserializer extends StdDeserializer<NamedTimeZone> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2540464328716350290L;

	public NamedTimeZoneDeserializer() {
		this(null);
	}

	public NamedTimeZoneDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public NamedTimeZone deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final JsonNode node = jp.getCodec().readTree(jp);
		final String timeZoneId = node.get("timeZone").asText();
		return TimeZoneList.getInstance().getNamedTimeZone(timeZoneId);
	}

}
