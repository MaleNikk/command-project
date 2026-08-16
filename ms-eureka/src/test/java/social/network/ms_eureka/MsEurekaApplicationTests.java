package social.network.ms_eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import social.network.ms_eureka.config.TestConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest(classes = TestConfiguration.class)
class MsEurekaApplicationTests {

	private final Logger logger = Logger.getLogger(MsEurekaApplicationTests.class.getName());

	@Test
	void contextLoads() {
		logger.log(Level.INFO,"Test initialise context complete successfully!");
	}
}