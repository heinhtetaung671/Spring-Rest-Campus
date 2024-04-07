package com.jdc.weekend.api.input;

import com.jdc.weekend.model.Status;

public record EmployeeStatusForm(Status status, String reason) {

}
