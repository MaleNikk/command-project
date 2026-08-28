package social.network.ms.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import social.network.ms.users.config.TestServiceConfiguration;

import java.util.logging.Logger;

@SpringBootTest(classes = TestServiceConfiguration.class)
class MsAccountApplicationTests {

	private final ApplicationContext context;

	private final Logger logger = Logger.getLogger("TEST");

	public MsAccountApplicationTests(@Autowired ApplicationContext context) {
		this.context = context;
	}

	@Test
	@DisplayName("Test initialize context.")
	void contextLoads() {
		Assertions.assertNotNull(context);
		logger.info("Test initialize context complete successfully!");
	}
}