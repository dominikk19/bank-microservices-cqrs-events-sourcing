package pl.dkiszka.bank.account.dto;

import lombok.Getter;
import pl.dkiszka.bank.account.models.BankAccount;

import java.util.List;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Getter
public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;

    public AccountLookupResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookupResponse(String message) {
        super(message);
        this.accounts = List.of();
    }
}
