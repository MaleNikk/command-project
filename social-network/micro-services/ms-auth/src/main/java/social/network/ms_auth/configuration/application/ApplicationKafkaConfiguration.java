package social.network.ms_auth.configuration.application;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import social.network.ms_auth.logging.ApplicationLogger;

import java.util.Map;

@EnableKafka
@Configuration
public class ApplicationKafkaConfiguration {

    private final String bootstrapServers;

    private final String groupId;

    private final ApplicationLogger logger;

    public ApplicationKafkaConfiguration(
            @Value("${spring.kafka.bootstrap-servers}")
            String bootstrapServers,
            @Value("${spring.kafka.group-id}")
            String groupId,
            @Autowired
            ApplicationLogger logger) {
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        this.logger = logger;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        logger.printLog("Create bean: ProducerFactory for kafka template.", Level.INFO);
        return new DefaultKafkaProducerFactory<>(
                Map.of(
                        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
                )
        );
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        logger.printLog("Create bean: ConsumerFactory for kafka template.", Level.INFO);
        return new DefaultKafkaConsumerFactory<>(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                        ConsumerConfig.GROUP_ID_CONFIG, groupId
                )
        );
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        logger.printLog("Create bean: KafkaTemplate.", Level.INFO);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory(), Boolean.TRUE);
        template.setConsumerFactory(consumerFactory());
        return template;
    }
}
