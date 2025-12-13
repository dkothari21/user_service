package com.company.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.user.dto.UserDTO;
import com.company.user.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndGetUser() {
        String baseUrl = "http://localhost:" + port + "/api/users";
        UserDTO newUser = new UserDTO(null, "Integration User", "integration@example.com");

        // Create
        ResponseEntity<UserDTO> response = restTemplate.postForEntity(baseUrl, newUser, UserDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        UserDTO createdUser = response.getBody();
        assertNotNull(createdUser.getId());

        // Get
        ResponseEntity<UserDTO> getResponse = restTemplate.getForEntity(baseUrl + "/" + createdUser.getId(),
                UserDTO.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Integration User", getResponse.getBody().getName());

        // Cleanup
        userRepository.deleteById(createdUser.getId());
    }
}
