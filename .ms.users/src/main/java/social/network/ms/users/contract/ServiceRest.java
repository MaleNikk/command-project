package social.network.ms.users.contract;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.ms.users.model.dto.FullAccountDto;
import social.network.ms.users.model.dto.ShortAccountDto;
import social.network.ms.users.model.dto.UpdateAccountDto;

import java.util.Map;

@RequestMapping("/api/v1/account")
@RestController
public interface ServiceRest {

    /**
     * GET /api/v1/account/me:
     * Получение информации о текущем аккаунте.
     */
    @GetMapping("/me")
    ResponseEntity<ShortAccountDto> getCurrentAccount(@RequestHeader HttpHeaders headers);

    /**
     * PUT /api/v1/account/me:
     * Обновление аккаунта.
     */
    @PutMapping("/me")
    ResponseEntity<ShortAccountDto> updateCurrentAccount(@RequestBody UpdateAccountDto update, @RequestHeader HttpHeaders headers);

    /**
     * DELETE /api/v1/account/me:
     * Пометка текущего аккаунта как удалённого.
     */
    @DeleteMapping("/me")
    ResponseEntity<Void> markCurrentAccountAsDeleted(@RequestHeader HttpHeaders headers);

    /**
     * GET /api/v1/account?email=...:
     * Получение аккаунта по email.
     */
    @GetMapping
    ResponseEntity<FullAccountDto> getAccountByEmail(@RequestParam String email, @RequestHeader HttpHeaders headers);

    /**
     * POST /api/v1/account:
     * Создание нового аккаунта.
     */
    @PostMapping
    ResponseEntity<ShortAccountDto> createAccount(@RequestBody ShortAccountDto account, @RequestHeader HttpHeaders headers);

    /**
     * POST /api/v1/account/lastAction/{uuid}:
     * Прием UUID от сервиса Dialogs.
     */
    @PostMapping("/lastAction/{id}")
    ResponseEntity<Void> receiveUUIDFromPath(@PathVariable String id, @RequestHeader HttpHeaders headers);

    /**
     * GET /api/v1/account/{id}:
     * Получение аккаунта по ID.
     */
    @GetMapping("/{id}")
    ResponseEntity<FullAccountDto> getAccountById(@PathVariable String id, @RequestHeader HttpHeaders headers);

    /**
     * DELETE /api/v1/account/{id}:
     * Пометка аккаунта как удалённого по ID.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> markAccountAsDeletedById(@PathVariable String id, @RequestHeader HttpHeaders headers);

    /**
     * PATCH /api/v1/account/{id}:
     * Пометка аккаунта как заблокированного по ID.
     */
    @PatchMapping("/{id}")
    ResponseEntity<Void> markAccountAsBlockedById(@PathVariable String id, @RequestHeader HttpHeaders headers);

    /**
     * GET /api/v1/account/total:
     * Получение общего количества аккаунтов.
     */
    @GetMapping("/total")
    ResponseEntity<Long> getTotalAccountsCount(@RequestHeader HttpHeaders headers);

    /**
     * GET /api/v1/account/search:
     * Глобальный поиск аккаунтов по ключевым словам.
     */
    @GetMapping("/search")
    ResponseEntity<Map<String, ShortAccountDto>> searchAccounts(@RequestParam Map<String, String> allParams,
                                                                @RequestHeader HttpHeaders headers);

    /**
     * GET /api/v1/account/search/statusCode:
     * Поиск аккаунтов по статус-коду.
     */
    @GetMapping("/search/statusCode")
    ResponseEntity<Map<String, ShortAccountDto>> searchByStatusCode(@RequestParam Map<String, String> allParams,
                                                                    @RequestHeader HttpHeaders headers);
}