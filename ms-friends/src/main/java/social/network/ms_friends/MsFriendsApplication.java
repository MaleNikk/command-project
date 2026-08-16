package social.network.ms_friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsFriendsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsFriendsApplication.class, args);
	}
}