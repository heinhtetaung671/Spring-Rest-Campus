package com.jdc.weekend.model.service;

import static com.jdc.weekend.model.Common.getOne;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.weekend.api.input.CategoryForm;
import com.jdc.weekend.api.input.CategorySearch;
import com.jdc.weekend.api.output.CategoryInfo;
import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	private static final String DOMAIN = "Category";

	@Transactional(readOnly = true)
	public List<CategoryInfo> search(CategorySearch search) {
		return repo.search(queryFunc(search));
	}

	public List<CategoryInfo> upload(MultipartFile file) {
		return null;
	}

	public CategoryInfo update(int id, CategoryForm form) {
		var entity = getOne(repo.findById(id), DOMAIN , id);
		entity.setName(form.name());
		entity.setDesciption(form.remark());
		entity.setType(form.type());
		return CategoryInfo.from(entity);
	}

	@Transactional(readOnly = true)
	public CategoryInfo findById(int id) {
		return CategoryInfo.from(getOne(repo.findById(id), DOMAIN , id));
	}

	public CategoryInfo create(CategoryForm form) {
		var entity = repo.save(form.getEntity());
		return CategoryInfo.from(entity);
	}

	private Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> queryFunc(CategorySearch search){
		return cb -> {
			var cq = cb.createQuery( CategoryInfo.class);
			var root = cq.from(Category.class);
			CategoryInfo.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}
	
}
