package com.plomteux.gptconnector;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("test")
class GptConnectorApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> GptConnectorApplication.main(new String[]{"--spring.profiles.active=test"}));
    }

}
