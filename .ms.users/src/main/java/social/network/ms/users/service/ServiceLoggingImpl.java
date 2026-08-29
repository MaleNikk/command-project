package social.network.ms.users.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import social.network.ms.users.contract.ServiceLogging;

@Slf4j
@Component
public class ServiceLoggingImpl implements ServiceLogging {

    @Override
    public void printInfo(String message) {
        log.info(message);
    }

    @Override
    public void printDebug(String message) {
        log.debug(message);
    }

    @Override
    public void printError(String message) {
        log.error(message);
    }

    @Override
    public void printTrace(String message) {
        log.trace(message);
    }
}