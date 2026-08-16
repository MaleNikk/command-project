package social.network.ms_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@EnableDiscoveryClient
@SpringBootApplication
@EnableKafka
@ComponentScan(basePackages = "social.network.ms_notifications")
public class MsNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificationsApplication.class, args);
	}
}