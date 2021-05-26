package com.workmotion.task.service;

import java.util.List;

import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel create(EmployeeModel employeeModel);
	
	public EmployeeModel updateState(Long id, EmployeeEventsEnum event);
	
	public EmployeeModel getById(Long id);
	
	public List<EmployeeModel> getAll();
	
}
