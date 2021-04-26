package pl.dkiszka.bank.account.controllers;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.account.dto.AccountLookupResponse;
import pl.dkiszka.bank.account.queries.FindAccountByIdQuery;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
class AccountLookupController {
    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    ResponseEntity<AccountLookupResponse> getAccountByID(@PathVariable String id) {

        return Try.of(() -> {
            var query = new FindAccountByIdQuery(id);
            var responseBody = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            return ResponseEntity.ok(responseBody);
        })
                .toOption()
                .getOrElse(ResponseEntity.noContent().build());
    }
}
