package com.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeConfig {

	@Value("${addressservice.base.url}")
	private String addressBaseUrl;
	
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		 return new ModelMapper();
	}

	
}
