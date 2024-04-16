package com.jdc.weekend.model.service;

import static com.jdc.weekend.model.common.Common.getOne;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.weekend.api.input.CategoryForm;
import com.jdc.weekend.api.input.CategorySearch;
import com.jdc.weekend.api.output.CategoryForSelectBox;
import com.jdc.weekend.api.output.CategoryInfo;
import com.jdc.weekend.model.constant.DomainNamesForExceptionMsg;
import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepo repo;

	public List<CategoryInfo> search(CategorySearch search) {
		return repo.search(queryFunc(search));
	}

	@Transactional
	public List<CategoryInfo> upload(MultipartFile file) {
		return null;
	}

	@Transactional
	public CategoryInfo update(int id, CategoryForm form) {
		var entity = getOne(repo.findById(id), DomainNamesForExceptionMsg.CATEGORY, id);
		entity.setName(form.name());
		entity.setDesciption(form.description());
		entity.setType(form.type());
		return CategoryInfo.from(entity);
	}

	public CategoryInfo findById(int id) {
		return CategoryInfo.from(getOne(repo.findById(id), DomainNamesForExceptionMsg.CATEGORY, id));
	}

	@Transactional
	public CategoryInfo create(CategoryForm form) {
		var entity = repo.save(form.getEntity());
		return CategoryInfo.from(entity);
	}

	private Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> queryFunc(CategorySearch search) {
		return cb -> {
			var cq = cb.createQuery(CategoryInfo.class);
			var root = cq.from(Category.class);
			CategoryInfo.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	public List<CategoryForSelectBox> loadAllForSelectBox() {
		Function<CriteriaBuilder, CriteriaQuery<CategoryForSelectBox>> queryFunc = cb -> {
			var cq = cb.createQuery(CategoryForSelectBox.class);
			var root = cq.from(Category.class);
			cq.multiselect(root.get(Category_.id), root.get(Category_.name));
			cq.orderBy(cb.asc(root.get(Category_.name)));
			return cq;
		};
		return repo.search(queryFunc);
	}

}
