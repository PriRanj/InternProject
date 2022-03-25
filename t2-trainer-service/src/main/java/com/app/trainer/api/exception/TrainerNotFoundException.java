package com.app.trainer.api.exception;

public class TrainerNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String msg; 
	
	public TrainerNotFoundException() {
		this.msg = "Trainer Not Found"; 
	}
	
	public TrainerNotFoundException(String msg) {
		this.msg = msg; 
	}
	
	@Override
	public String toString() {
		return msg; 
	}
}

