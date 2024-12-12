package com.challenge.customer;

import com.challenge.customer.configuration.ApplicationPropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({ApplicationPropertiesConfiguration.class})
public class ChlMsaCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChlMsaCustomerApplication.class, args);
	}

}
