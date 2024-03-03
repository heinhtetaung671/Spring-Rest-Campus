package com.jdc.weekend.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.DepartmentCreateForm;
import com.jdc.weekend.api.input.DepartmentSearch;
import com.jdc.weekend.api.input.DepartmentUpdateForm;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.api.output.DataModificationResult;
import com.jdc.weekend.api.output.DepartmentInfo;
import com.jdc.weekend.api.output.DepartmentInfoDetails;
import com.jdc.weekend.model.NullSafeHelper;
import com.jdc.weekend.model.entity.Department;
import com.jdc.weekend.model.repo.DepartmentRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

	@Autowired
	private DepartmentRepo repo;
	
	private static final String DOMAIN_NAME = "Department";
	
	@Transactional
	public DataModificationResult<String> create(DepartmentCreateForm form) {
		var department = repo.save(form.entity());
		return DataModificationResult.createResult(department.getCode(), DOMAIN_NAME, "code");
	}

	public DepartmentInfoDetails findById(String code) {
		return NullSafeHelper.safeCall(repo.findById(code).map(DepartmentInfoDetails::from), DOMAIN_NAME, code);
	}

	@Transactional
	public DataModificationResult<String> update(String code, DepartmentUpdateForm form) {
		var department = NullSafeHelper.safeCall(repo.findById(code), DOMAIN_NAME, "code");
		department.setName(form.name());
		department.setPhone(form.phone());
		department.setDescription(form.description());
		return DataModificationResult.updateResult(code, DOMAIN_NAME, "code");
	}

	public List<DepartmentInfo> search(DepartmentSearch search) {
		return repo.search(searchFunc(search));
	}

	private Function<CriteriaBuilder, CriteriaQuery<DepartmentInfo>> searchFunc(DepartmentSearch searchForm){
		return cb -> {
			var cq = cb.createQuery(DepartmentInfo.class);
			var root = cq.from(Department.class);
			DepartmentInfo.select(cb, cq, root);
			cq.where(searchForm.where(cb, root));
			return cq;
		};
	}
	
}
