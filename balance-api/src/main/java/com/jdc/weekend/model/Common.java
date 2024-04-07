package com.jdc.weekend.model;

import java.util.Optional;

import com.jdc.weekend.model.exception.ApiBusinessException;

public class Common {

	public static <T, ID> T getOne(Optional<T> optional, String domain, ID id){
		return optional.orElseThrow(() -> new ApiBusinessException("There is no %s with id %s.".formatted(domain, id)));
	}
	
}
