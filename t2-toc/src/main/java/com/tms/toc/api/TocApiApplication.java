package com.tms.toc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TocApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TocApiApplication.class, args);
	}

}
