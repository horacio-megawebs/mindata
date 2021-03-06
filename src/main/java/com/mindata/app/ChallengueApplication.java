package com.mindata.app;

import com.mindata.app.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ChallengueApplication {

	static final Logger logger = LoggerFactory.getLogger(ChallengueApplication.class);

	public static void main(String[] args) {
		logger.info("Logback ok");
		SpringApplication.run(ChallengueApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
