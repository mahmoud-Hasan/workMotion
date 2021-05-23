package com.workmotion.task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkmotionErrors {
	INVALID_STATE_TRANSACTION("1001","invalid state transaction");
	
	private final String code;
	private final String message;
	
}
