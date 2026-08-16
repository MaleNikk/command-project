package social.network.ms_account.network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms_account.logging.ApplicationLogging;
import social.network.ms_account.model.dto.FullAccountDto;
import social.network.ms_account.model.dto.ShortAccountDto;
import social.network.ms_account.model.dto.UpdateAccountDto;
import social.network.ms_account.service.AccountsService;

import java.util.Map;

@RequestMapping("/api/v1/account")
@RestController
public class MicroServiceAccountsController {

    private final ApplicationLogging logging;

    private final AccountsService service;

    @Autowired
    public MicroServiceAccountsController(ApplicationLogging logging, AccountsService service) {
        this.logging = logging;
        this.service = service;
    }

    /**
     * GET /api/v1/account/me:
     * Получение информации о текущем аккаунте.
     */
    @GetMapping("/me")
    public ResponseEntity<ShortAccountDto> getCurrentAccount(@RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to get current account info");
        ShortAccountDto account = service.getShortAccount(headers);
        if (account != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * PUT /api/v1/account/me:
     * Обновление аккаунта.
     */
    @PutMapping("/me")
    public ResponseEntity<ShortAccountDto> updateCurrentAccount(@RequestBody UpdateAccountDto update, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to update current account");
        ShortAccountDto account = service.editAccount(headers, update);
        if (account != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * DELETE /api/v1/account/me:
     * Пометка текущего аккаунта как удалённого.
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> markCurrentAccountAsDeleted(@RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to mark current account as deleted");
        if (service.removeAccount(headers) != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * GET /api/v1/account?email=...:
     * Получение аккаунта по email.
     */
    @GetMapping
    public ResponseEntity<FullAccountDto> getAccountByEmail(@RequestParam String email, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to get account by email: " + email);
        return ResponseEntity.ok(service.getFullAccountByEmail(email, headers));
    }

    /**
     * POST /api/v1/account:
     * Создание нового аккаунта.
     */
    @PostMapping
    public ResponseEntity<ShortAccountDto> createAccount(@RequestBody ShortAccountDto account, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to create a new account");
        ShortAccountDto saved = service.registerAccount(account, headers);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * POST /api/v1/account/lastAction/{uuid}:
     * Прием UUID от сервиса Dialogs.
     */
    @PostMapping("/lastAction/{id}")
    public ResponseEntity<Void> receiveUUIDFromPath(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received to method lastAction.");
        return ResponseEntity.ok().build();
    }

    /**
     * GET /api/v1/account/{id}:
     * Получение аккаунта по ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FullAccountDto> getAccountById(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to get account by id.");
        FullAccountDto saved = service.getFullAccountById(id, headers);
        if (saved != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * DELETE /api/v1/account/{id}:
     * Пометка аккаунта как удалённого по ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> markAccountAsDeletedById(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to mark account as deleted by ID: " + id);
        FullAccountDto deleted = service.removeAccount(id, headers);
        if (deleted != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * PATCH /api/v1/account/{id}:
     * Пометка аккаунта как заблокированного по ID.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> markAccountAsBlockedById(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to mark account as blocked by ID: " + id);
        Boolean isBlocked = service.setBlocked(id, headers);
        if (isBlocked != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * GET /api/v1/account/total:
     * Получение общего количества аккаунтов.
     */
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalAccountsCount(@RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to get total accounts count");
        Long countAccounts = service.getCountAccounts(headers);
        if (countAccounts != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * GET /api/v1/account/search:
     * Глобальный поиск аккаунтов по ключевым словам.
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, ShortAccountDto>> searchAccounts(@RequestParam Map<String, String> allParams,
                                                                       @RequestHeader HttpHeaders headers) {
        logging.printInfo("Received request to search accounts with params by words.");
        Map<String, ShortAccountDto> result = service.searchAccounts(allParams, headers);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * GET /api/v1/account/search/statusCode:
     * Поиск аккаунтов по статус-коду.
     */
    @GetMapping("/search/statusCode")
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