package com.app.employee.api.exception;

/**
 * 
 * If  Employee doesn't found in the database this custom exception will be thrown.
 * @author Priya Ranajn
 * @since Feb 2022
 *
 */
public class EmployeeNotFoundException extends Exception{
		
		private static final long serialVersionUID = 1L;
		private String msg; 
		
		public EmployeeNotFoundException() {
			this.msg = "Employee Not Found !!"; 
		}
		
		public EmployeeNotFoundException(String msg) {
			super();
			this.msg = msg; 
		}
		
		@Override
		public String toString() {
			return msg; 
		}

}
