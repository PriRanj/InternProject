package com.tms.toc.api.exception;

/**
 * 
 * If a particular course doesn't found in the database this custom exception will be thrown.
 * @author Sangita Halder
 * @since Feb 2022
 *
 */

public class TOCNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public TOCNotFoundException() {
		this.msg = "TOC not found!!";
	}

	public TOCNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}
	
	
}
