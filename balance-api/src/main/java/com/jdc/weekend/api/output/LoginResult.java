package com.jdc.weekend.api.output;

import java.time.LocalDateTime;

public record LoginResult(LocalDateTime expiredTime, String token) {

}
