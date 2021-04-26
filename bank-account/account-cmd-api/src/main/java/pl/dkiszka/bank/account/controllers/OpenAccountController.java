package pl.dkiszka.bank.account.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.account.commands.OpenAccountCommand;
import pl.dkiszka.bank.account.dto.OpenAccountResponse;
import pl.dkiszka.bank.account.services.AccountService;

import javax.validation.Valid;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/accounts")
@RequiredArgsConstructor
class OpenAccountController {
    private final AccountService accountService;

    @PostMapping
    ResponseEntity<OpenAccountResponse> openAccount(@Valid @RequestBody OpenAccountCommand command) {
        return ResponseEntity.ok(accountService.openAccount(command));
    }
}
