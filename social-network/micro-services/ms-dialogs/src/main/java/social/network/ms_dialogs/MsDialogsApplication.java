package social.network.ms_dialogs;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsDialogsApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(MsDialogsApplication.class)
				.web(WebApplicationType.SERVLET)
				.main(MsDialogsApplication.class)
				.lazyInitialization(Boolean.TRUE)
				.run(args);
	}
}