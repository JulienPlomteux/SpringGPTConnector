package com.plomteux.gptconnector.service;

import com.plomteux.gptconnector.model.ChatGPTRequest;
import com.plomteux.gptconnector.model.ChatGPTResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ChatServiceTest {
    @Autowired
    private ChatService chatService;
    @MockBean
    private RestTemplate restTemplate;

    @Value("${chatgpt.api.key}")
    private String API_KEY;

    @Value("${chatgpt.api.endpoint}")
    private String API_ENDPOINT;

    @Test
    public void generateText_returnsExpectedResponse() {
        // Arrange
        ChatGPTRequest request = new ChatGPTRequest();
        ChatGPTResponse expectedResponse = new ChatGPTResponse();
        ResponseEntity<ChatGPTResponse> expectedEntity = ResponseEntity.ok(expectedResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        HttpEntity<ChatGPTRequest> expectedEntityRequest = new HttpEntity<>(request, headers);

        when(restTemplate.exchange(eq(API_ENDPOINT), eq(HttpMethod.POST),
                eq(expectedEntityRequest), eq(ChatGPTResponse.class)))
                .thenReturn(expectedEntity);

        // Act
        ResponseEntity<ChatGPTResponse> actualResponse = chatService.generateText(request);

        // Assert
        assertEquals(expectedResponse, actualResponse.getBody());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

        verify(restTemplate, times(1)).exchange(eq(API_ENDPOINT), eq(HttpMethod.POST),
                eq(expectedEntityRequest), eq(ChatGPTResponse.class));
    }
}
