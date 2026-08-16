package social.network.ms_account.service;

import org.springframework.http.HttpHeaders;
import social.network.ms_account.model.dto.FullAccountDto;
import social.network.ms_account.model.dto.ShortAccountDto;
import social.network.ms_account.model.dto.StatusCode;
import social.network.ms_account.model.dto.UpdateAccountDto;

import java.util.Map;

public interface AccountsService {

    ShortAccountDto setStatus(StatusCode statusCode);

    ShortAccountDto registerAccount(ShortAccountDto account, HttpHeaders headers);

    ShortAccountDto getShortAccount(HttpHeaders headers);

    ShortAccountDto editAccount(HttpHeaders headers, UpdateAccountDto update);

    FullAccountDto getFullAccountByEmail(String email, HttpHeaders headers);

    FullAccountDto getFullAccountById(String id, HttpHeaders headers);

    FullAccountDto removeAccount(String id, HttpHeaders headers);

    FullAccountDto removeAccount(HttpHeaders headers);

    Boolean setBlocked(String id, HttpHeaders headers);

    Long getCountAccounts(HttpHeaders headers);

    Map<String, ShortAccountDto> searchAccounts(Map<String, String> allParams, HttpHeaders headers);
}