package com.jdc.weekend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.api.input.BalanceSearch;
import com.jdc.weekend.api.output.BalanceReport;
import com.jdc.weekend.model.service.BalanceService;

@RestController
@RequestMapping("balance")
public class BalanceReportApi {

	@Autowired
	private BalanceService service;
	
	@GetMapping
	BalanceReport search(BalanceSearch search, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "0") int size) {
		return service.search(search, page, size);
	}

}
