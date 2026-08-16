package social.network.ms_auth.kafka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import social.network.ms_auth.configuration.TestApplicationConfiguration;

@SpringBootTest(classes = TestApplicationConfiguration.class)
public class TestApplicationKafka {

    @Test
    @DisplayName("Test connection with kafka.")
    public void testConnectionWithKafka() {

    }

    @Test
    @DisplayName("Test send data to kafka.")
    public void testSendDataToKafka() {

    }

    @Test
    @DisplayName("Test read data from kafka.")
    public void testReadDataFromKafka() {

    }
}