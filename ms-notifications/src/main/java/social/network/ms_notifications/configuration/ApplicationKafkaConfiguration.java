package social.network.ms_notifications.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@EnableKafka
@Configuration
public class ApplicationKafkaConfiguration {

    @Value("${application.kafka.URI}")
    private String uri;

    @Value("${application.kafka.groupId}")
    private String groupId;

    @Bean
    public Map<String,Object> producerConfigs() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, uri,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );
    }

    @Bean
    public Map<String,Object> consumerConfigs() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, uri,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, groupId
        );
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ConsumerFactory<String,Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaTemplate<?,?> kafkaTemplate() {
        KafkaTemplate<String,Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setConsumerFactory(consumerFactory());
        kafkaTemplate.setCloseTimeout(Duration.of(5, TimeUnit.SECONDS.toChronoUnit()));
        return kafkaTemplate;
    }
}