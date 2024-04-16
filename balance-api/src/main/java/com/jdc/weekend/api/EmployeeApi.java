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

import com.jdc.weekend.api.input.EmployeeForm;
import com.jdc.weekend.api.input.EmployeeSearch;
import com.jdc.weekend.api.input.EmployeeStatusForm;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.api.output.EmployeeInfo;
import com.jdc.weekend.api.output.EmployeeInfoDetails;
import com.jdc.weekend.model.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeApi {

	@Autowired
	private EmployeeService service;
	
	@GetMapping
	ApiResponse<Page<EmployeeInfo>> search(EmployeeSearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(search, page, size));
	}
	
	@GetMapping("{id}")
	ApiResponse<EmployeeInfoDetails> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}
	
	@GetMapping("{id}/edit")
	ApiResponse<EmployeeForm> findByIdForEdit(@PathVariable int id) {
		return ApiResponse.success(service.findByIdForEdit(id));
	}
	
	@PostMapping
	ApiResponse<EmployeeInfo> create(@RequestBody @Validated EmployeeForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{id}")
	ApiResponse<EmployeeInfo> update(@PathVariable int id, @RequestBody @Validated EmployeeForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}
	
	@PutMapping("{id}/status")
	ApiResponse<EmployeeInfo> updateStatus(@PathVariable int id, @RequestBody @Validated EmployeeStatusForm form, BindingResult result) {
		return ApiResponse.success(service.updateStatus(id, form));
	}
	
}
