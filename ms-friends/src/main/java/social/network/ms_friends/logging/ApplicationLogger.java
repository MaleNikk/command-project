package social.network.ms_friends.logging;

import org.slf4j.event.Level;

@FunctionalInterface
public interface ApplicationLogger {
    void printLog(String message, Level level);
}