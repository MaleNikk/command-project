package social.network.ms_dialogs.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;
import java.util.logging.Logger;

@Configuration
public class ApplicationKafkaConfiguration {
    @Value("${application.kafka.URI}")
    private String uri;
    @Value("${application.kafka.groupId}")
    private String groupId;

    @Bean
    public Map<String, Object> producerConfigs() {
        Logger.getLogger("CONFIGURATION").info("Kafka uri: " + uri + " Kafka group: " + groupId);
        return Map.of("bootstrap.servers", uri,
                "key.deserializer", StringDeserializer.class,
                "value.deserializer", JsonDeserializer.class,
                "group.id", groupId);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
