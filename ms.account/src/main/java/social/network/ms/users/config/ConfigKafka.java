package social.network.ms.users.config;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.kafka.autoconfigure.KafkaConnectionDetails;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.List;

/**
 * Configuration: Kafka listen factory, producer & consumer
 */

@Configuration
@EnableKafka
public class ConfigKafka implements KafkaConnectionDetails {
    @Override
    public List<String> getBootstrapServers() {
        return List.of();
    }

    @Override
    public @Nullable SslBundle getSslBundle() {
        return KafkaConnectionDetails.super.getSslBundle();
    }

    @Override
    public @Nullable String getSecurityProtocol() {
        return KafkaConnectionDetails.super.getSecurityProtocol();
    }

    @Override
    public Configuration getConsumer() {
        return KafkaConnectionDetails.super.getConsumer();
    }

    @Override
    public Configuration getProducer() {
        return KafkaConnectionDetails.super.getProducer();
    }

    @Override
    public Configuration getAdmin() {
        return KafkaConnectionDetails.super.getAdmin();
    }

    @Override
    public Configuration getStreams() {
        return KafkaConnectionDetails.super.getStreams();
    }
}