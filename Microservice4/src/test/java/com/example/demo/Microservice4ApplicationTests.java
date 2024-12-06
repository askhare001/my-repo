package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest( webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class Microservice4ApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private MockRestServiceServer mockServer;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCallM2()
	{
		mockServer=MockRestServiceServer.createServer(restTemplate);
		
		mockServer.expect(requestTo("http://localhost:8086/service3"))
		.andRespond(withSuccess("Mocked response from service 3", MediaType.TEXT_PLAIN));
		
		ResponseEntity<String> response=testRestTemplate.getForEntity("http://localhost:" +port + "/call-microservice3", String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Mocked resonse from service 3");
		mockServer.verify();
		
	}

}
