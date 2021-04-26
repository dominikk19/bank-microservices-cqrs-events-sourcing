package pl.dkiszka.bank.account.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.account.commands.DepositFundsCommand;
import pl.dkiszka.bank.account.commands.WithdrawFundsCommand;
import pl.dkiszka.bank.account.dto.BaseResponse;
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
class FundOperationOnAccountController {
    private final AccountService accountService;


    @PutMapping(path = "/{id}/withdraw")
    ResponseEntity<BaseResponse> withdrawFunds(@PathVariable String id,
                                               @Valid @RequestBody WithdrawFundsCommand command) {
        return ResponseEntity.ok(accountService.withdrawFunds(id, command));
    }

    @PutMapping(path = "/{id}/deposit")
    ResponseEntity<BaseResponse> depositFunds(@PathVariable String id,
                                              @Valid @RequestBody DepositFundsCommand command) {
        return ResponseEntity.ok(accountService.depositFunds(id, command));
    }
}
