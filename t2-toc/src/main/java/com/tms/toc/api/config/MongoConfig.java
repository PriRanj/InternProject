package com.tms.toc.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.tms.toc.api.repo.TOCRepository;

/**
 * 
 * @author Sangita Halder
 * @see contains configuaration details of mongodb
 * @since Feb 2022
 *
 */

@Configuration
@EnableMongoRepositories(basePackageClasses = TOCRepository.class)
public class MongoConfig {

	public CommandLineRunner commandLineRunner(TOCRepository tocRepo){
		
		return null;
	}
}
