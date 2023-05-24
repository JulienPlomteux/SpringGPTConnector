package com.plomteux.gptconnector.controller;

import com.plomteux.gptconnector.model.ChatGPTRequest;
import com.plomteux.gptconnector.model.ChatGPTResponse;
import com.plomteux.gptconnector.service.ChatService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ChatControllerApiImpl implements ChatControllerApi {
    private ChatService chatService;
    private static final Logger logger = LoggerFactory.getLogger(ChatControllerApiImpl.class);

    @Override
    public ResponseEntity<ChatGPTResponse> sendMessage(ChatGPTRequest chatGPTRequest) {
        logger.debug("Received request: {}", chatGPTRequest);
        return chatService.generateText(chatGPTRequest);
    }
}
