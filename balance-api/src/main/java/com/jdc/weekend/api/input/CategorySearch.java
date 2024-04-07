package com.jdc.weekend.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.weekend.model.BalanceType;
import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CategorySearch(BalanceType type, String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Category> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(null != type) {
			list.add(cb.equal(root.get(Category_.type), type));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Category_.name)), name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}

}
