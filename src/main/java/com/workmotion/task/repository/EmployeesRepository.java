package com.workmotion.task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.workmotion.task.model.EmployeeModel;

public interface EmployeesRepository extends MongoRepository<EmployeeModel,Long> {

}
