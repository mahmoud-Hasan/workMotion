package com.workmotion.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workmotion.task.model.EmployeeModel;
import com.workmotion.task.model.EmployeeStatesEnum;


@SpringBootTest
public class EmployeeServiceRealTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void createEmployeeWithAddedStatu() {
		// Assert that new employees should be created with the status ADDED 
		EmployeeModel employeeModel=EmployeeModel.builder()
									.age(30).firstName("firstName")
									.lastName("lastName").state(EmployeeStatesEnum.ACTIVE)
									.build();
		employeeModel =employeeService.createEmployee(employeeModel);
		assertEquals(employeeModel.getState(), EmployeeStatesEnum.ADDED);
	}
}
