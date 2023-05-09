package com.plomteux.gptconnector.service;

import com.plomteux.gptconnector.model.ChatGPTRequest;
import com.plomteux.gptconnector.model.ChatGPTResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ChatService {
    private RestTemplate restTemplate;

    @Value("${chatgpt.api.endpoint}")
    private String CHATGPT_API_ENDPOINT;
    @Value("${chatgpt.api.key}")
    private String CHATGPT_API_KEY;

    public ResponseEntity<ChatGPTResponse> generateText(ChatGPTRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.CHATGPT_API_KEY);

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(chatGptRequest, headers);

        return restTemplate.exchange(
                this.CHATGPT_API_ENDPOINT,
                HttpMethod.POST,
                entity,
                ChatGPTResponse.class
        );
    }
}