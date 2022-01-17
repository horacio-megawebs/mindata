package com.mindata.app;

import com.mindata.app.config.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.profiles.active = test"})
class ChallengueApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ApplicationProperties applicationProperties;

	static final Logger logger = LoggerFactory.getLogger(ChallengueApplicationTests.class);


	@Test
	void contextLoads() {
	}


	@Test
	public void getHello() throws Exception {
		logger.debug("server " + applicationProperties.getServer().getHost());
		String baseUri = "http://" + applicationProperties.getServer().getHost() + ":" +
				applicationProperties.getServer().getPort() ;
		mvc.perform(MockMvcRequestBuilders.get(baseUri + "/api/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World!")));
	}

	@Test
	void testHello() {


		Assertions.assertEquals("Hello World!",
				restTemplate.getForEntity(URI.create("/api/hello"), String.class));
	}


}
