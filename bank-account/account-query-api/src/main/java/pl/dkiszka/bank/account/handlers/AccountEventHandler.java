package pl.dkiszka.bank.account.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.dkiszka.bank.account.events.AccountClosedEvent;
import pl.dkiszka.bank.account.events.AccountOpenedEvent;
import pl.dkiszka.bank.account.events.FundsDepositedEvent;
import pl.dkiszka.bank.account.events.FundsWithdrawnEvent;
import pl.dkiszka.bank.account.models.BankAccount;
import pl.dkiszka.bank.account.repositories.AccountRepository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Component
@RequiredArgsConstructor
class AccountEventHandler {
    private final AccountRepository accountRepository;

    @EventHandler
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .creationDate(event.getCreationDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @EventHandler
    public void on(FundsDepositedEvent event) {
        accountRepository.findById(event.getId())
                .ifPresent(account -> {
                    account.setBalance(event.getBalance());
                    accountRepository.save(account);
                });
    }

    @EventHandler
    public void on(FundsWithdrawnEvent event) {
        accountRepository.findById(event.getId())
                .ifPresent(account -> {
                    account.setBalance(event.getBalance());
                    accountRepository.save(account);
                });
    }

    @EventHandler
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }

}
