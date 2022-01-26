package com.mindata.app;

import com.mindata.app.config.ApplicationProperties;
import com.mindata.app.model.SuperHeroe;
import com.mindata.app.rest.SuperHeroeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.profiles.active = test"})
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class ChallengueApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ApplicationProperties applicationProperties;

	static final Logger logger = LoggerFactory.getLogger(ChallengueApplicationTests.class);

	@Value("${spring.security.user.name}")
	String userName;

	@Value("${spring.security.user.password}")
	String password;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
	}


	@Test
	public void getHello() throws Exception {
		logger.debug("server " + applicationProperties.getServer().getHost());
		String baseUri = "http://" + applicationProperties.getServer().getHost() + ":" +
				applicationProperties.getServer().getPort() ;
		mvc.perform(MockMvcRequestBuilders.get(baseUri + "/api/hello"). accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World!")));
	}

	@Test
	void testHellowithTestRestTemplate() {
		ResponseEntity<String> responseEntity = testRestTemplate.withBasicAuth( userName, password )
				.getForEntity( URI.create( (new StringBuilder())
											.append( "http://" )
											.append( applicationProperties.getServer().getHost() )
											.append( ":" )
											.append( applicationProperties.getServer().getPort() )
											.append( "/api/hello" ).toString()) , String.class);
		Assertions.assertEquals("Hello World!", responseEntity.getBody());
	}

	@Test
	void testNewSuperHeroe() {
		SuperHeroeDTO superHeroeDTO = SuperHeroeDTO.builder()
													.id(1)
													.nombre("Heman")
													.build();
		ResponseEntity<SuperHeroeDTO> responseEntity = testRestTemplate.withBasicAuth( userName, password)
				.postForEntity( URI.create( (new StringBuilder())
											.append( "http://" )
											.append( applicationProperties.getServer().getHost() )
											.append( ":" )
											.append( applicationProperties.getServer().getPort() )
											.append( "/api/new" ).toString()) , superHeroeDTO, SuperHeroeDTO.class);
		Assertions.assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
	}

}
