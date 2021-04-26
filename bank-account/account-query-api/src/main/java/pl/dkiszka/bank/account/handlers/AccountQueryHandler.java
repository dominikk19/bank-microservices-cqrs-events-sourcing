package pl.dkiszka.bank.account.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import pl.dkiszka.bank.account.dto.AccountLookupResponse;
import pl.dkiszka.bank.account.queries.FindAccountByIdQuery;
import pl.dkiszka.bank.account.repositories.AccountRepository;

import java.util.List;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Component
@RequiredArgsConstructor
class AccountQueryHandler {
    private final AccountRepository accountRepository;


    @QueryHandler
    AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
       return accountRepository.findById(query.getId())
                .map(account -> new AccountLookupResponse("Bank Account Successfully Returned!", List.of(account)))
                .orElseGet( ()-> new AccountLookupResponse("No Bank Account Found for ID - " + query.getId()));
    }

}
