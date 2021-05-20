package com.workmotion.task.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import com.workmotion.task.model.EmployeeEventsEnum;
import com.workmotion.task.model.EmployeeStatesEnum;

@Configuration
@EnableStateMachine
public class StateConfig extends StateMachineConfigurerAdapter<EmployeeStatesEnum, EmployeeEventsEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<EmployeeStatesEnum, EmployeeEventsEnum> states) 
      throws Exception {
 
        states
          .withStates()
          .initial(EmployeeStatesEnum.ADDED)
          .states(EnumSet.allOf(EmployeeStatesEnum.class))
          .end(EmployeeStatesEnum.ACTIVE);

    }

    @Override
    public void configure(
      StateMachineTransitionConfigurer<EmployeeStatesEnum, EmployeeEventsEnum> transitions) 
      throws Exception {
 
        transitions.withExternal()
          .source(EmployeeStatesEnum.ADDED).target(EmployeeStatesEnum.IN_CHECK).event(EmployeeEventsEnum.CHECK).and()
          .withExternal()
          .source(EmployeeStatesEnum.IN_CHECK).target(EmployeeStatesEnum.APPROVED).event(EmployeeEventsEnum.APPROVE).and()
          .withExternal()
          .source(EmployeeStatesEnum.APPROVED).target(EmployeeStatesEnum.ACTIVE).event(EmployeeEventsEnum.ACTIVATE)
          ;
    }
    
   @Override
   public void configure(StateMachineConfigurationConfigurer<EmployeeStatesEnum, EmployeeEventsEnum> config)
		throws Exception {
	   
	   StateMachineListenerAdapter<EmployeeStatesEnum, EmployeeEventsEnum> stateAdapter = new StateMachineListenerAdapter<>() {
		   public void stateChanged(State<EmployeeStatesEnum, EmployeeEventsEnum> from,
				   State<EmployeeStatesEnum, EmployeeEventsEnum> to) {
			   System.out.println("state changed");
		   };
	   };
	   config.withConfiguration().listener(stateAdapter);
}
}