package social.network.ms_eureka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsEurekaApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MsEurekaApplication.class)
				.web(WebApplicationType.SERVLET)
				.lazyInitialization(Boolean.TRUE)
				.run(args);
	}
}