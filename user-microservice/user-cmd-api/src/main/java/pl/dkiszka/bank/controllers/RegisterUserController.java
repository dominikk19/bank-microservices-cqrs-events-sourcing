package pl.dkiszka.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.commands.RegisterUserCommand;
import pl.dkiszka.bank.dto.RegisterUserResponse;
import pl.dkiszka.bank.services.UserService;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
class RegisterUserController {
    private final UserService userService;

    @PostMapping
    @ResponseBody
    ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command) {
        return ResponseEntity.ok(userService.registerUser(command));
    }
}
