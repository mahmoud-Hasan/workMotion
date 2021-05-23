package com.workmotion.task.exception;

import lombok.Getter;

public class InvalidStateTransactionException extends RuntimeException {

	private static final long serialVersionUID = -8342694395503247203L;
	
	@Getter
	private final String code;
	
	public InvalidStateTransactionException(WorkmotionErrors error) {
		super(error.getMessage());
		this.code = error.getCode();
	}
}