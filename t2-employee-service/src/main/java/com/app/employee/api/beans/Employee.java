package com.app.employee.api.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Document(collection="employees")
public class Employee {
	@Id
	private int id;
	
	//basic details
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	
	//address details
	private String city;
	private int pin;
	private String state;
	private String country;
	

}
