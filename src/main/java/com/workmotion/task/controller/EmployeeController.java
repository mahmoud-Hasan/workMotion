package com.workmotion.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/get")
	public ResponseEntity<List<EmployeeModel>> getAll() {
		return ResponseEntity.ok(employeeService.getAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeModel> getById(@PathVariable Long id ) {
		return ResponseEntity.ok(employeeService.getById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeModel> create(@RequestBody @Valid EmployeeModel employeeModel){
		
		return ResponseEntity.ok(employeeService.create(employeeModel));
	}
	
	@PatchMapping("/updatestate/{id}/state/{event}")
	public ResponseEntity<EmployeeModel> updateState(@PathVariable Long id ,@PathVariable EmployeeEventsEnum event ){
		
			return ResponseEntity.ok(employeeService.updateState(id, event));

	}
}
