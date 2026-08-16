package social.network.ms_auth.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import social.network.ms_auth.configuration.TestApplicationConfiguration;

@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TestApplicationService {

    @Test
    @DisplayName("Test call to methods service from controller.")
    public void testCallingMethodsFromController() {

    }

    @Test
    @DisplayName("Test calling entity for logic methods.")
    public void testEntityFromController() {

    }

    @Test
    @DisplayName("Test return response from service methods.")
    public void testReturnResponseFromService() {

    }

    @Test
    @DisplayName("Test return models from service.")
    public void testReturnModelsFromService() {

    }
}