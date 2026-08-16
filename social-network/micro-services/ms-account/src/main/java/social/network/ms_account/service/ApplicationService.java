package social.network.ms_account.service;

import org.springframework.stereotype.Component;
import social.network.ms_account.model.AccountFullDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationService {

    private final List<AccountFullDto> accounts;

    public ApplicationService() {
        this.accounts = new ArrayList<>();
    }

    public void createAccounts(int count) {
        for (int i = 0; i <= count; i++) {
            AccountFullDto account = new AccountFullDto();
            accounts.add();
        }
    }
}