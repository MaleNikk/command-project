package social.network.ms.users.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.network.ms.users.contract.ServiceLogging;

@Service
public class ServiceLoggingImpl implements ServiceLogging {
    private final Logger logger;

    @Autowired
    public ServiceLoggingImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void printInfo(String message) {
        logger.info(message);
    }

    @Override
    public void printDebug(String message) {
        logger.debug(message);
    }

    @Override
    public void printError(String message) {
        logger.error(message);
    }

    @Override
    public void printTrace(String message) {
        logger.trace(message);
    }
}