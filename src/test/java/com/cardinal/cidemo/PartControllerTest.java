package com.cardinal.cidemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PartControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PartController partController;

    @Test
    public void test_getAllParts() {
        List<Part> parts = Arrays.stream(this.restTemplate.getForObject("http://localhost:" + port + "/parts",
            Part[].class)).collect(Collectors.toList());

        assertThat(parts)
            .hasSize(2)
            .extracting(Part::getPartNumber)
            .containsExactlyInAnyOrder("PART1234", "BUMPER");
    }

}