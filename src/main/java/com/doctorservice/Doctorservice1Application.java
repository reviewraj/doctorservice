package com.doctorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class Doctorservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(Doctorservice1Application.class, args);
	}

}
