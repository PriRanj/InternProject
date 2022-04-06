package com.app.tmseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TmsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsEurekaServerApplication.class, args);
	}

}
