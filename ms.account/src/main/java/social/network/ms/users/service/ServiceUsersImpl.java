package social.network.ms.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import social.network.ms.users.contract.*;
import social.network.ms.users.mapper.UserModelMapper;
import social.network.ms.users.model.dto.*;

import java.util.Map;
import java.util.UUID;

@Service
public class ServiceUsersImpl implements ServiceUsers {

    private final UserModelMapper mapper;

    private final ServiceStorage storage;

    private final ServiceKafka serviceNotifications;

    private final ServiceSecurity serviceSecurity;

    private final ServiceLogging logging;

    @Autowired
    public ServiceUsersImpl(
            UserModelMapper mapper, ServiceStorage storage,
            ServiceKafka serviceNotifications,
            ServiceSecurity serviceSecurity,
            ServiceLogging logging) {
        this.mapper = mapper;
        this.storage = storage;
        this.serviceNotifications = serviceNotifications;
        this.serviceSecurity = serviceSecurity;
        this.logging = logging;
    }

    @Override
    public ShortAccountDto setStatus(StatusCode statusCode) {
        logging.printInfo("Accounts service: set status account.");
        return null;
    }

    @Override
    public ShortAccountDto registerAccount(ShortAccountDto account, HttpHeaders headers) {
        logging.printInfo("Accounts service: register new account.");
        if (serviceSecurity.checkToken(headers)) {
            serviceNotifications.sendNotification(mapper.getNotification(account, NotificationType.REGISTER));
            return mapper.shortFrom(storage.saveAccount(mapper.from(account)));
        }
        return null;
    }

    @Override
    public ShortAccountDto getShortAccount(HttpHeaders headers) {
        logging.printInfo("Accounts service: get short info account.");
        if (serviceSecurity.checkToken(headers)) {
            UUID id = serviceSecurity.getId(headers);
            return id == null ? null : mapper.shortFrom(storage.getAccountById(id));
        }
        return null;
    }

    @Override
    public ShortAccountDto editAccount(HttpHeaders headers, UpdateAccountDto update) {
        logging.printInfo("Accounts service: edit account.");
        if (serviceSecurity.checkToken(headers)) {
            ShortAccountDto account = mapper.shortFrom(storage.editAccount(mapper.from(update, serviceSecurity.getId(headers))));
            serviceNotifications.sendNotification(mapper.getNotification(account, NotificationType.UPDATE));
            return account;
        }
        return null;
    }

    @Override
    public FullAccountDto getFullAccountByEmail(String email, HttpHeaders headers) {
        logging.printInfo("Accounts service: get full info account by email.");
        if (serviceSecurity.checkToken(headers)) {
            return mapper.fullFrom(storage.getAccountByEmail(email));
        }
        return null;
    }

    @Override
    public FullAccountDto getFullAccountById(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: get full info account by id.");
        if (serviceSecurity.checkToken(headers)) {
            return mapper.fullFrom(storage.getAccountById(UUID.fromString(id)));
        }
        return null;
    }

    @Override
    public FullAccountDto removeAccount(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: remove account by id.");
        if (serviceSecurity.checkToken(headers)) {
            FullAccountDto account = mapper.fullFrom(storage.deleteAccount(UUID.fromString(id)));
            serviceNotifications.sendNotification(mapper.getNotification(account, NotificationType.DELETE));
            return account;
        }
        return null;
    }

    @Override
    public FullAccountDto removeAccount(HttpHeaders headers) {
        logging.printInfo("Accounts service: mark account as deleted.");
        if (serviceSecurity.checkToken(headers)) {
            return mapper.fullFrom(storage.deleteAccount(serviceSecurity.getId(headers)));
        }
        return null;
    }

    @Override
    public Boolean setBlocked(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: blocked account by id.");
        if (serviceSecurity.checkToken(headers)) {
            ShortAccountDto account = mapper.shortFrom(storage.setBlocked(UUID.fromString(id)));
            serviceNotifications.sendNotification(mapper.getNotification(account, NotificationType.DELETE));
            return Boolean.TRUE;
        }
        return null;
    }

    @Override
    public Long getCountAccounts(HttpHeaders headers) {
        logging.printInfo("Accounts service: get count accounts.");
        if (serviceSecurity.checkToken(headers)) {
            return (long) storage.getAllAccounts().size();
        }
        return null;
    }

    @Override
    public Map<String, ShortAccountDto> searchAccounts(Map<String, String> allParams, HttpHeaders headers) {
        logging.printInfo("Accounts service: search accounts by param.");
        allParams.forEach((key, value) -> System.out.println(key + " - " + value));
        return Map.of();
    }
}