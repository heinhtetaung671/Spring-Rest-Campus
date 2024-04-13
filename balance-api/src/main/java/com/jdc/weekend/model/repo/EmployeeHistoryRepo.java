package com.jdc.weekend.model.repo;

import org.springframework.data.jpa.repository.Query;

import com.jdc.weekend.model.BaseRepo;
import com.jdc.weekend.model.entity.EmployeeHistory;
import com.jdc.weekend.model.entity.EmployeeHistroyPk;

public interface EmployeeHistoryRepo extends BaseRepo<EmployeeHistory, EmployeeHistroyPk>{

	@Query("SELECT COALESCE(MAX(hist.id.seqNumber),0) + 1 FROM EmployeeHistory hist WHERE hist.id.employeeId = ?1")
	int getNextSeqNumber(int employeeId);
	
}
