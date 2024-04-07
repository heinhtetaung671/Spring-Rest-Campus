package com.jdc.weekend.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resources")
public class UserInformationApi {

	@GetMapping
	List<String> getInfo(){
		return List.of("Hello form user api.");
	}
	
}
