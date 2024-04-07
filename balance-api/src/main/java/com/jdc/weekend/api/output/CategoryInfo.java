package com.jdc.weekend.api.output;

import com.jdc.weekend.model.BalanceType;
import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CategoryInfo(int id, String name, BalanceType type, String remark) {

	public static CategoryInfo from(Category category) {
		return null;
	}

	public static void select(CriteriaQuery<CategoryInfo> cq, Root<Category> root) {
		cq.multiselect(
				root.get(Category_.id),
				root.get(Category_.name),
				root.get(Category_.type),
				root.get(Category_.desciption)
				);
	}
	
}
