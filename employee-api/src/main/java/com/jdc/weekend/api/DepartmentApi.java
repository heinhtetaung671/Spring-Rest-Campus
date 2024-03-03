package com.jdc.weekend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.api.input.DepartmentCreateForm;
import com.jdc.weekend.api.input.DepartmentSearch;
import com.jdc.weekend.api.input.DepartmentUpdateForm;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.api.output.DataModificationResult;
import com.jdc.weekend.api.output.DepartmentInfo;
import com.jdc.weekend.api.output.DepartmentInfoDetails;
import com.jdc.weekend.model.service.DepartmentService;


@RestController
@RequestMapping("department")
public class DepartmentApi {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	ApiResponse<List<DepartmentInfo> > search(DepartmentSearch search){
		
		return ApiResponse.success(departmentService.search(search));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(@Validated @RequestBody DepartmentCreateForm form, BindingResult result){
		return ApiResponse.success(departmentService.create(form));
	}

	@GetMapping("{code}")
	ApiResponse<DepartmentInfoDetails> findById(@PathVariable String code){
		return ApiResponse.success(departmentService.findById(code));
	}

	@PostMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,@Validated @RequestBody DepartmentUpdateForm form, BindingResult result){
		return ApiResponse.success(departmentService.update(code, form));
	}
	
}
