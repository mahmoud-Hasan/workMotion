package com.workmotion.task.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "employees")
public class EmployeeModel implements Serializable{

	private static final long serialVersionUID = 1L;
			
	@Id
	private Long id;
	
	@Transient
    public static final String SEQUENCE_NAME = "employees_sequence";

	
	private EmployeeStatesEnum state;
	
	@Field(name = "first_name")
	private String firstName;
	
	@Field(name = "last_name")
	private String lastName;
	
	private Integer age;
}