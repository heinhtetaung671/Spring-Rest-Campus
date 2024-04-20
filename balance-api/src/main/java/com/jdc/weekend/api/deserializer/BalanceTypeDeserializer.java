package com.jdc.weekend.api.deserializer;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jdc.weekend.model.constant.BalanceType;

@Component
public class BalanceTypeDeserializer extends JsonDeserializer<BalanceType>{

	@Override
	public BalanceType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		var type = p.getText();
		if(StringUtils.hasLength(type)) {
			return BalanceType.valueOf(type);
		}
		return null;
	}

}
