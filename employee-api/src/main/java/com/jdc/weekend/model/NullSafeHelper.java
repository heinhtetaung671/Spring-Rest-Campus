package com.jdc.weekend.model;

import java.util.Optional;

public class NullSafeHelper {

	public static <T, ID> T safeCall(Optional<T> optional, String domainName, ID id) {
		return optional.orElseThrow(() -> new BusinessException("There is no %s with %s".formatted(domainName, id)));
	}
	
}
