package com.plomteux.gptconnector.controller;

import com.plomteux.gptconnector.model.ChatGPTRequest;
import com.plomteux.gptconnector.model.ChatGPTResponse;
import com.plomteux.gptconnector.service.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ChatControllerApiImplTest {
    @MockBean
    private ChatService chatService;

    @Autowired
    private ChatControllerApiImpl chatControllerApi;

    @Test
    public void sendMessage_returnsResponseFromChatService() {
        // Arrange
        ChatGPTRequest request = new ChatGPTRequest();
        ChatGPTResponse expectedResponse = new ChatGPTResponse();
        ResponseEntity<ChatGPTResponse> expectedEntity = ResponseEntity.ok(expectedResponse);

        when(chatService.generateText(request)).thenReturn(expectedEntity);

        // Act
        ResponseEntity<ChatGPTResponse> actualResponse = chatControllerApi.sendMessage(request);

        // Assert
        assertEquals(expectedResponse, actualResponse.getBody());
        assertEquals(expectedEntity.getStatusCode(), actualResponse.getStatusCode());

        verify(chatService, times(1)).generateText(request);
    }

}