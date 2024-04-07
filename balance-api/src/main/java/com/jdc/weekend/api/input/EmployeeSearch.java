package com.jdc.weekend.api.input;

import com.jdc.weekend.model.EmployeeRole;
import com.jdc.weekend.model.Status;

public record EmployeeSearch(EmployeeRole role,Status status, String keyword) {

}
