package social.network.ms_account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import social.network.ms_account.logging.ApplicationLogging;
import social.network.ms_account.mapper.DtoMapper;
import social.network.ms_account.model.dto.*;
import social.network.ms_account.notification.NotificationsService;
import social.network.ms_account.security.ServiceSecurity;
import social.network.ms_account.storage.AccountsStorage;

import java.util.Map;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountsService {

    private final AccountsStorage storage;

    private final NotificationsService notificationsService;

    private final ServiceSecurity serviceSecurity;

    private final ApplicationLogging logging;

    @Autowired
    public AccountServiceImpl(
            AccountsStorage storage,
            NotificationsService notificationsService,
            ServiceSecurity serviceSecurity,
            ApplicationLogging logging) {
        this.storage = storage;
        this.notificationsService = notificationsService;
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
            notificationsService.sendNotification(DtoMapper.getNotification(account, NotificationType.REGISTER));
            return DtoMapper.shortFrom(storage.saveAccount(DtoMapper.from(account)));
        }
        return null;
    }

    @Override
    public ShortAccountDto getShortAccount(HttpHeaders headers) {
        logging.printInfo("Accounts service: get short info account.");
        if (serviceSecurity.checkToken(headers)) {
            UUID id = serviceSecurity.getId(headers);
            return id == null ? null : DtoMapper.shortFrom(storage.getAccountById(id));
        }
        return null;
    }

    @Override
    public ShortAccountDto editAccount(HttpHeaders headers, UpdateAccountDto update) {
        logging.printInfo("Accounts service: edit account.");
        if (serviceSecurity.checkToken(headers)) {
            ShortAccountDto account = DtoMapper.shortFrom(storage.editAccount(DtoMapper.from(update, serviceSecurity.getId(headers))));
            notificationsService.sendNotification(DtoMapper.getNotification(account, NotificationType.UPDATE));
            return account;
        }
        return null;
    }

    @Override
    public FullAccountDto getFullAccountByEmail(String email, HttpHeaders headers) {
        logging.printInfo("Accounts service: get full info account by email.");
        if (serviceSecurity.checkToken(headers)) {
            return DtoMapper.fullFrom(storage.getAccountByEmail(email));
        }
        return null;
    }

    @Override
    public FullAccountDto getFullAccountById(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: get full info account by id.");
        if (serviceSecurity.checkToken(headers)) {
            return DtoMapper.fullFrom(storage.getAccountById(UUID.fromString(id)));
        }
        return null;
    }

    @Override
    public FullAccountDto removeAccount(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: remove account by id.");
        if (serviceSecurity.checkToken(headers)) {
            FullAccountDto account = DtoMapper.fullFrom(storage.deleteAccount(UUID.fromString(id)));
            notificationsService.sendNotification(DtoMapper.getNotification(account, NotificationType.DELETE));
            return account;
        }
        return null;
    }

    @Override
    public FullAccountDto removeAccount(HttpHeaders headers) {
        logging.printInfo("Accounts service: mark account as deleted.");
        if (serviceSecurity.checkToken(headers)) {
            return DtoMapper.fullFrom(storage.deleteAccount(serviceSecurity.getId(headers)));
        }
        return null;
    }

    @Override
    public Boolean setBlocked(String id, HttpHeaders headers) {
        logging.printInfo("Accounts service: blocked account by id.");
        if (serviceSecurity.checkToken(headers)) {
            ShortAccountDto account = DtoMapper.shortFrom(storage.setBlocked(UUID.fromString(id)));
            notificationsService.sendNotification(DtoMapper.getNotification(account, NotificationType.DELETE));
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