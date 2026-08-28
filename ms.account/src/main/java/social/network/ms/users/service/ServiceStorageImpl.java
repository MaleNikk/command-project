package social.network.ms.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import social.network.ms.users.contract.ServiceStorage;
import social.network.ms.users.exception.NotFoundUserException;
import social.network.ms.users.exception.EditUserInfoException;
import social.network.ms.users.contract.ServiceLogging;
import social.network.ms.users.model.entity.AccountEntity;
import social.network.ms.users.model.entity.UpdateEntity;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ServiceStorageImpl implements ServiceStorage {

    private final Map<UUID, AccountEntity> storage;

    private final ServiceLogging logging;

    public ServiceStorageImpl(@Autowired ServiceLogging logging) {
        this.logging = logging;
        this.storage = new HashMap<>();
    }

    @Override
    public AccountEntity saveAccount(AccountEntity account) {
        logging.printDebug("Account storage class: save new account.");
        if (account != null) {
            account.setCreatedOn(LocalDateTime.now());
            storage.put(account.getId(), account);
        } else {
            throw new NotFoundUserException("Account storage class: Entity for save is null!");
        }
        return storage.get(account.getId());
    }

    @Override
    public AccountEntity editAccount(UpdateEntity account) {
        logging.printDebug("Account storage class: edit account.");
        if (account != null) {
            AccountEntity edited = storage.get(account.getId());
            edited.setFirstName(account.getFirstName() == null ? edited.getFirstName() : account.getFirstName());
            edited.setLastName(account.getLastName() == null ? edited.getLastName() : account.getLastName());
            edited.setPhone(account.getPhone() == null ? edited.getPhone() : account.getPhone());
            edited.setPhoto(account.getPhoto() == null ? edited.getPhoto() : account.getPhoto());
            edited.setProfileCover(account.getProfileCover() == null ? edited.getProfileCover() : account.getProfileCover());
            edited.setAbout(account.getAbout() == null ? edited.getAbout() : account.getAbout());
            edited.setCountry(account.getCountry() == null ? edited.getCountry() : account.getCountry());
            edited.setCity(account.getCity() == null ? edited.getCity() : account.getCity());
            edited.setBirthDate(account.getBirthDate() == null ? edited.getBirthDate() : account.getBirthDate());
            edited.setEmojiStatus(account.getEmojiStatus() == null ? edited.getEmojiStatus() : account.getEmojiStatus());
            edited.setUpdatedOn(LocalDateTime.now());
        } else {
            throw new EditUserInfoException("Account storage class: Entity for update is null!");
        }
        return null;
    }

    @Override
    public AccountEntity deleteAccount(UUID id) {
        logging.printDebug("Account storage class: delete account.");
        AccountEntity deleted = null;
        if (storage.containsKey(id)) {
            deleted = storage.get(id);
            deleted.setLastOnlineTime(LocalDateTime.now());
            deleted.setDeletionTimestamp(LocalDateTime.now());
            deleted.setDeleted(Boolean.TRUE);
        }
        return deleted;
    }

    @Override
    public AccountEntity getAccountByEmail(String email) {
        logging.printDebug("Account storage class: get account by email.");
        List<AccountEntity> accounts =
                getAllAccounts().stream().filter(account -> Objects.equals(account.getEmail(), email)).toList();
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public AccountEntity getAccountById(UUID id) {
        logging.printDebug("Account storage class: get account by id.");
        return storage.getOrDefault(id, null);
    }

    @Override
    public AccountEntity setStatus(UUID id, String statusCode) {
        logging.printDebug("Account storage class: set status account.");
        if (storage.containsKey(id)) {
            AccountEntity account = storage.get(id);
            account.setStatusCode(statusCode);
            return account;
        }
        return null;
    }

    @Override
    public AccountEntity setBlocked(UUID id) {
        logging.printDebug("Account storage class: set blocked account by id.");
        if (storage.containsKey(id)) {
            AccountEntity account = storage.get(id);
            account.setBlocked(Boolean.TRUE);
            return account;
        } else {
            return null;
        }
    }

    @Override
    public List<AccountEntity> getAllAccounts() {
        logging.printDebug("Account storage class: get all accounts.");
        return new ArrayList<>(storage.values());
    }
}