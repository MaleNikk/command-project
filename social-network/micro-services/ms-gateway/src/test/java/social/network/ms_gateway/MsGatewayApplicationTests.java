package social.network.ms_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
class MsGatewayApplicationTests {

	private final Logger logger = Logger.getLogger("Test logging!");

	@Test
	void contextLoads() {
		logger.log(Level.INFO, "Test initialise context complete successfully!");
	}
}