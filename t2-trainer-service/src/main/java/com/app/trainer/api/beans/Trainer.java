package com.app.trainer.api.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@ToString
@AllArgsConstructor
@Document(collection="Trainer")
public class Trainer {

	@Transient
	public static final String SEQUENCE_NAME = "trainer_sequence";
	@Id

	private int id;
	
	private String firstname;
	private String lastname;
	private String email;
	private String mobile;
	private String password;
	
	//address details
	private String city;
	private int pin;
	private String state;
	private String country;
	

}
