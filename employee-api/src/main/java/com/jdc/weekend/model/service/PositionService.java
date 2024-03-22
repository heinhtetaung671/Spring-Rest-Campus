package com.jdc.weekend.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.PositionCreateForm;
import com.jdc.weekend.api.input.PositionSearch;
import com.jdc.weekend.api.input.PositionUpdateForm;
import com.jdc.weekend.api.output.DataModificationResult;
import com.jdc.weekend.api.output.PositionInfo;
import com.jdc.weekend.api.output.PositionInfoDetails;
import com.jdc.weekend.model.DataModificationResults;
import com.jdc.weekend.model.NullSafeHelper;
import com.jdc.weekend.model.entity.Position;
import com.jdc.weekend.model.repo.PositionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepo repo;
	
	private static final String DOMAIN_NAME = "Position";
	
	public List<PositionInfo> search(PositionSearch search) {
		
		return repo.search(searchFunc(search));
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<PositionInfo>> searchFunc(PositionSearch search) {
		return cb -> {
			var cq = cb.createQuery(PositionInfo.class);
			var root = cq.from(Position.class);
			PositionInfo.select(cb, root, cq);
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	@Transactional
	public DataModificationResult<String> create(PositionCreateForm form) {
		var entity =  repo.save(form.entity());
		return DataModificationResults.createResult(entity.getCode(), DOMAIN_NAME, "Code");
	}

	@Transactional
	public DataModificationResult<String> update(String code, PositionUpdateForm form) {
		var entity = NullSafeHelper.safeCall(repo.findById(code), DOMAIN_NAME, code);
		entity.setName(form.name());
		entity.setBasicSalary(form.basicSalary());
		entity.setOtPerHour(form.otPerHour());
		entity.setRemark(form.remark());
		return DataModificationResults.updateResult(code, DOMAIN_NAME, "code");
	}

	public PositionInfoDetails findById(String code) {
		return PositionInfoDetails.from(NullSafeHelper.safeCall(repo.findById(code), DOMAIN_NAME, code));
	}
	
}
