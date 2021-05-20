package com.workmotion.task.service;

import java.util.List;

import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel createEmployee(EmployeeModel employeeModel);
	
	public EmployeeModel updateEmpolyeeStates(Long employeId, EmployeeEventsEnum event);
	
	public EmployeeModel getEmployeeById(Long id);
	
	public List<EmployeeModel> getAllEmployees();
	
}
