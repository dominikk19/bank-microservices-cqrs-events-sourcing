package pl.dkiszka.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.commands.UpdateUserCommand;
import pl.dkiszka.bank.dto.BaseResponse;
import pl.dkiszka.bank.services.UserService;

import javax.validation.Valid;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
class UpdateUserController {
    private final UserService userService;

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    ResponseEntity<BaseResponse> registerUser(@PathVariable String id, @Valid @RequestBody UpdateUserCommand command) {
        return ResponseEntity.ok(userService.updateUser(id, command));
    }
}
