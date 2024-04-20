package com.jdc.weekend.api.deserializer;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jdc.weekend.model.constant.Role;

@Component
public class RoleDeserializer extends JsonDeserializer<Role>{

	@Override
	public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		var role = p.getText();
		if(StringUtils.hasLength(role)) {
			return Role.valueOf(role);
		}
		return null;
	}

}
