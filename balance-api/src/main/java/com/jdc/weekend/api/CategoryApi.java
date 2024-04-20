package com.jdc.weekend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.weekend.api.input.CategoryForm;
import com.jdc.weekend.api.input.CategorySearch;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.api.output.CategoryForSelectBox;
import com.jdc.weekend.api.output.CategoryInfo;
import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryApi {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	ApiResponse<List<CategoryInfo>> search(CategorySearch search){
		return ApiResponse.success(service.search(search));
	}
	
	@PostMapping("upload")
	ApiResponse<List<CategoryInfo>> upload(MultipartFile file){
		return ApiResponse.success(service.upload(file));
	}
	
	@PostMapping
	ApiResponse<CategoryInfo> create(@RequestBody @Validated CategoryForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@GetMapping("{id}")
	ApiResponse<CategoryInfo> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}
	
	@PutMapping("{id}")
	ApiResponse<CategoryInfo> update(@PathVariable int id,@RequestBody  @Validated CategoryForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}
	
	@GetMapping("{type}/for-select-box")
	ApiResponse<List<CategoryForSelectBox>> loadAllForSelectBox(@PathVariable BalanceType type){
		return ApiResponse.success(service.loadAllForSelectBox(type));
	}
	
}
