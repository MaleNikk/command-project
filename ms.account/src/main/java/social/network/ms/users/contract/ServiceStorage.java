package social.network.ms.users.contract;

import social.network.ms.users.model.entity.AccountEntity;
import social.network.ms.users.model.entity.UpdateEntity;

import java.util.List;
import java.util.UUID;

public interface ServiceStorage {

    AccountEntity saveAccount(AccountEntity account);

    AccountEntity editAccount(UpdateEntity account);

    AccountEntity deleteAccount(UUID id);

    AccountEntity getAccountByEmail(String email);

    AccountEntity getAccountById(UUID id);

    AccountEntity setStatus(UUID id, String statusCode);

    AccountEntity setBlocked(UUID id);

    List<AccountEntity> getAllAccounts();
}
