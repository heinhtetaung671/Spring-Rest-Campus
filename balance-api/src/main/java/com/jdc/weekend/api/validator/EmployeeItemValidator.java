package com.jdc.weekend.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jdc.weekend.api.input.LedgerEntryForm;

@Component
public class EmployeeItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(LedgerEntryForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof LedgerEntryForm(var issueDate, var categoryId, var remark, var items)) {
			
			if(issueDate == null) {
				errors.rejectValue("issueDate", "issueDate", "Please select issueDate");
			}
			
			if(categoryId <= 0 ) {
				errors.rejectValue("categoryId", "categoryId", "Please select category.");
			}
			
			for (int i = 0; i < items.size(); i++) {
				var buffer = new StringBuffer("");
				
				var item = items.get(i);
				boolean hasError = false;
				if (!StringUtils.hasLength(item.name())) {
					buffer.append("Please enter name. ");
					hasError = true;
				}

				if (item.quantity() <= 0) {
					buffer.append("Please enter quantity > 0. ");
					hasError = true;
				}

				if (item.unitPrice() == null || item.unitPrice().intValue() <= 0) {
					buffer.append("Please enter unitPrice > 0. ");
					hasError = true;
				}

				if (hasError) {
					buffer.append("at row = ");
					buffer.append(i + 1);
					errors.rejectValue("items", "items", buffer.toString());
				}
			}
		}
	}

}
