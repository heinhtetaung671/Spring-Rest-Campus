package com.jdc.weekend.api.output;

import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CategoryInfo(int id, String name, BalanceType type, String description) {

	public static CategoryInfo from(Category category) {
		return new CategoryInfo(category.getId(), category.getName(), category.getType(), category.getDesciption());
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
