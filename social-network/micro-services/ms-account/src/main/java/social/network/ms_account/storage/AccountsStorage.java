package social.network.ms_account.storage;

import social.network.ms_account.model.entity.AccountEntity;
import social.network.ms_account.model.entity.UpdateEntity;

import java.util.List;
import java.util.UUID;

public interface AccountsStorage {

    AccountEntity saveAccount(AccountEntity account);

    AccountEntity editAccount(UpdateEntity account);

    AccountEntity deleteAccount(UUID id);

    AccountEntity getAccountByEmail(String email);

    AccountEntity getAccountById(UUID id);

    AccountEntity setStatus(UUID id, String statusCode);

    AccountEntity setBlocked(UUID id);

    List<AccountEntity> getAllAccounts();
}
