package com.jdc.weekend.api.output;

import java.util.List;

public record EmployeeInfoDetails(EmployeeInfo info, List<EmployeeHistoryInfo> history) {

}
