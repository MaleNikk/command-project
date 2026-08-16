package social.network.ms_geo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGeoApplication.class, args);
	}
}