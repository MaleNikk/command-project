package social.network.ms.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import social.network.ms.users.contract.ServiceLogging;
import social.network.ms.users.contract.ServiceRest;
import social.network.ms.users.model.dto.FullAccountDto;
import social.network.ms.users.model.dto.ShortAccountDto;
import social.network.ms.users.model.dto.UpdateAccountDto;
import social.network.ms.users.contract.ServiceUsers;

import java.util.Map;

@Service
public class ServiceRestImpl implements ServiceRest {

    private final ServiceLogging logging;

    private final ServiceUsers service;

    @Autowired
    public ServiceRestImpl(ServiceLogging logging, ServiceUsers service) {
        this.logging = logging;
        this.service = service;
    }

    @Override
    public ResponseEntity<ShortAccountDto> getCurrentAccount(HttpHeaders headers) {
        logging.printInfo("Received request to get current account info");
        ShortAccountDto account = service.getShortAccount(headers);
        if (account != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<ShortAccountDto> updateCurrentAccount(UpdateAccountDto update, HttpHeaders headers) {
        logging.printInfo("Received request to update current account");
        ShortAccountDto account = service.editAccount(headers, update);
        if (account != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> markCurrentAccountAsDeleted(HttpHeaders headers) {
        logging.printInfo("Received request to mark current account as deleted");
        if (service.removeAccount(headers) != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<FullAccountDto> getAccountByEmail(String email, HttpHeaders headers) {
        logging.printInfo("Received request to get account by email: " + email);
        return ResponseEntity.ok(service.getFullAccountByEmail(email, headers));
    }

    @Override
    public ResponseEntity<ShortAccountDto> createAccount(ShortAccountDto account, HttpHeaders headers) {
        logging.printInfo("Received request to create a new account");
        ShortAccountDto saved = service.registerAccount(account, headers);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Void> receiveUUIDFromPath(String id, HttpHeaders headers) {
        logging.printInfo("Received to method lastAction.");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<FullAccountDto> getAccountById(String id, HttpHeaders headers) {
        logging.printInfo("Received request to get account by id.");
        FullAccountDto saved = service.getFullAccountById(id, headers);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> markAccountAsDeletedById(String id, HttpHeaders headers) {
        logging.printInfo("Received request to mark account as deleted by ID: " + id);
        FullAccountDto deleted = service.removeAccount(id, headers);
        if (deleted != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> markAccountAsBlockedById(String id, HttpHeaders headers) {
        logging.printInfo("Received request to mark account as blocked by ID: " + id);
        Boolean isBlocked = service.setBlocked(id, headers);
        if (isBlocked != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Long> getTotalAccountsCount(HttpHeaders headers) {
        logging.printInfo("Received request to get total accounts count");
        Long countAccounts = service.getCountAccounts(headers);
        if (countAccounts != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Map<String, ShortAccountDto>> searchAccounts(Map<String, String> allParams, HttpHeaders headers) {
        logging.printInfo("Received request to search accounts with params by words.");
        Map<String, ShortAccountDto> result = service.searchAccounts(allParams, headers);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Map<String, ShortAccountDto>> searchByStatusCode(@RequestParam Map<String, String> allParams,
                                                                           @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to search accounts by status code with params by status code.");
        Map<String, ShortAccountDto> result = service.searchAccounts(allParams, headers);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}