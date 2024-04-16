package com.jdc.weekend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.api.input.LedgerEntryForm;
import com.jdc.weekend.api.input.LedgerEntrySearch;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.api.output.LedgerEntryDetails;
import com.jdc.weekend.api.output.LedgerEntryInfo;
import com.jdc.weekend.model.service.LedgerEntryService;

@RestController
@RequestMapping("ledger")
public class LedgetEntryApi {

	@Autowired
	private LedgerEntryService service;
	
	@GetMapping
	ApiResponse<Page<LedgerEntryInfo>> search(LedgerEntrySearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size) {
		return ApiResponse.success(service.search(search, page, size));
	}
	
	@PostMapping
	ApiResponse<LedgerEntryInfo> create(@RequestBody @Validated LedgerEntryForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping
	ApiResponse<LedgerEntryInfo> update(@PathVariable String id, @RequestBody @Validated LedgerEntryForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}
	
	@GetMapping("{id}")
	ApiResponse<LedgerEntryDetails> findById(@PathVariable String id) {
		return ApiResponse.success(service.findById(id));
	}
	
	@GetMapping("{id}/edit")
	ApiResponse<LedgerEntryForm> findByIdForEdit(@PathVariable String id){
		return ApiResponse.success(service.findByIdForEdit(id));
	}
	
}
