package social.network.ms_geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootTest
class MsGeoApplicationTests {

	private final ApplicationContext context;

	private final Logger logger = Logger.getLogger("TEST");

	public MsGeoApplicationTests(@Autowired ApplicationContext context) {
		this.context = context;
	}

	@Test
	@DisplayName("Test initialize context.")
	void contextLoads() {
		Assertions.assertNotNull(context);
		logger.info("Test initialize context complete successfully!");
	}
}