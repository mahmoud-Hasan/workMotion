package com.workmotion.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import com.workmotion.task.model.EmployeeModel;
import com.workmotion.task.exception.InvalidStateTransactionException;
import com.workmotion.task.exception.WorkmotionErrors;
import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeStatesEnum;
import com.workmotion.task.repository.EmployeesRepository;
import com.workmotion.task.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Value(value = "${kafka.topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, EmployeeModel> kafkaTemplate;

	@Autowired
	private EmployeesRepository employeesRepo;

	@Autowired
	private SequenceGeneratorService generatorService;

	@Autowired
	private StateMachine<EmployeeStatesEnum, EmployeeEventsEnum> stateMachine;

	@Override
	public EmployeeModel create(EmployeeModel employeeModel) {
		employeeModel.setState(EmployeeStatesEnum.ADDED);
		employeeModel.setId(generatorService.generateSequence(EmployeeModel.SEQUENCE_NAME));
		employeeModel = employeesRepo.save(employeeModel);
		sendMessage(employeeModel);
		return employeeModel;
	}

	@Override
	public EmployeeModel updateState(Long id, EmployeeEventsEnum event) {
		EmployeeModel employeeModel = getById(id);
		EmployeeStatesEnum state = employeeModel.getState();
		stateMachine.getStateMachineAccessor().doWithAllRegions(access -> {
			access.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null, null));
		});
		stateMachine.start();
		if (!stateMachine.sendEvent(event))
			throw new InvalidStateTransactionException(WorkmotionErrors.INVALID_STATE_TRANSACTION);

		employeeModel.setState(stateMachine.getState().getId());
		stateMachine.stop();
		employeeModel = employeesRepo.save(employeeModel);

		return employeeModel;
	}

	@Override
	public EmployeeModel getById(Long id) {
		EmployeeModel employeeModel = employeesRepo.findById(id).orElse(null);
		return employeeModel;
	}

	@Override
	public List<EmployeeModel> getAll() {
		return employeesRepo.findAll();
	}

	private void sendMessage(EmployeeModel msg) {
//		sending the new user to a kafka topic for a consumer 
		kafkaTemplate.send(topicName, msg);
	}
}