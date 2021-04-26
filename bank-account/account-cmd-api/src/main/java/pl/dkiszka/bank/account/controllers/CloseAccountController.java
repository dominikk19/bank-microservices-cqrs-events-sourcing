package pl.dkiszka.bank.account.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.account.dto.BaseResponse;
import pl.dkiszka.bank.account.services.AccountService;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/accounts")
@RequiredArgsConstructor
class CloseAccountController {
    private final AccountService accountService;


    @DeleteMapping(path = "/{id}")
    ResponseEntity<BaseResponse> closeAccount(@PathVariable String id) {
        return ResponseEntity.ok(accountService.closeAccount(id));
    }
}
