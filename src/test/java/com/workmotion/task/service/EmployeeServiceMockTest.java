package com.workmotion.task.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workmotion.task.exception.InvalidStateTransactionException;
import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeModel;
import com.workmotion.task.model.EmployeeStatesEnum;
import com.workmotion.task.repository.EmployeesRepository;
import com.workmotion.task.service.impl.EmployeeServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceMockTest {

	@Mock
	private EmployeesRepository employeesRepo;
	
	@InjectMocks
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Test
	public void testStateChangingSequence() {
		//state change sequence " as configured in StateConfig class" is : Added -> In-check -> Approved -> Active
		//any different sequence transformation should throw an InvalidStateTransaction Exception
		
		EmployeeModel employeeModel=EmployeeModel.builder()
				.age(30).firstName("firstName")
				.lastName("lastName").state(EmployeeStatesEnum.ADDED)
				.build();
		when(employeesRepo.findById(1001L)).thenReturn(Optional.of(employeeModel));
		assertThrows( InvalidStateTransactionException.class , ()->{
			employeeServiceImpl.updateState(1001L, EmployeeEventsEnum.ACTIVATE);
			}
		);
	    
	}
}
