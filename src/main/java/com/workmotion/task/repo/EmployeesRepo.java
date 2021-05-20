package com.workmotion.task.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.workmotion.task.model.EmployeeModel;

public interface EmployeesRepo extends MongoRepository<EmployeeModel,Long> {

}
