package social.network.ms_auth;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MsAuthApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MsAuthApplication.class)
				.lazyInitialization(Boolean.TRUE)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}