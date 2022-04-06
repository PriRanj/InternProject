package com.tms.toc.api.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * beans class for TOC
 * @author Sangita Halder
 * @since Feb 2022
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="toc")
public class TOC {
	
	//course details
	@Id
	private int id;
	private String courseName;
	private int courseDuration;
	private String courseObjective;
	private String coursePreRequisites;
	private String courseFor;
	private int courseCreatedBy;
}
