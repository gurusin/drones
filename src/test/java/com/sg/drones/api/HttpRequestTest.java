package com.sg.drones.api;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    /*
    * TODO : This should be configured to connect to an in memory database like H2,
    * then pre populate with some reference data and verify.
    *
    * */
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {

        String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/drones",
                String.class);
        assertThat(result).isNotEmpty();
    }
}
