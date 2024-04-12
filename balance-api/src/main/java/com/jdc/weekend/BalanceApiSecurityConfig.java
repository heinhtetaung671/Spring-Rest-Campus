package com.jdc.weekend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/balance-api.properties")
public class BalanceApiSecurityConfig {

}
