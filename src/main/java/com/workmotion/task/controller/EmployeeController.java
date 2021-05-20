package com.workmotion.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeModel;
import com.workmotion.task.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/get/all")
	public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/get/{employeId}")
	public ResponseEntity<EmployeeModel> getEmplyeeById(@PathVariable Long employeId ) {
		return ResponseEntity.ok(employeeService.getEmployeeById(employeId));
	}
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel){
		
		return ResponseEntity.ok(employeeService.createEmployee(employeeModel));
	}
	
	@PatchMapping("/update/{employeId}/state/{event}")
	public ResponseEntity<?> createEmployee(@PathVariable Long employeId ,@PathVariable EmployeeEventsEnum event ){
		try {
			return ResponseEntity.ok(employeeService.updateEmpolyeeStates(employeId, event));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
