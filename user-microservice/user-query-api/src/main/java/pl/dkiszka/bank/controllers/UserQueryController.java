package pl.dkiszka.bank.controllers;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.dto.UserLookupResponse;
import pl.dkiszka.bank.services.UserQueryGatewayException;
import pl.dkiszka.bank.services.UserQueryService;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserQueryController {

    private static final Predicate<UserLookupResponse> isUsersNotEmpty = resp -> !resp.getUsers().isEmpty();
    private final UserQueryService userQueryService;


    @GetMapping
    @ResponseBody
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        return Option.of(userQueryService.getAllUsers())
                .filter(isUsersNotEmpty)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.noContent()::build);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id) {
        return Option.of(userQueryService.getUserById(id))
                .filter(isUsersNotEmpty)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.noContent()::build);
    }

    @GetMapping(value = "/byFilter")
    @ResponseBody
    public ResponseEntity<UserLookupResponse> searchUserByFilter(@RequestParam String filter) {
        return Option.of(userQueryService.findUsersByFilter(filter))
                .filter(isUsersNotEmpty)
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.noContent()::build);
    }

    @ExceptionHandler(value = {UserQueryGatewayException.class})
    ResponseEntity<String> userQueryGatewayException(UserQueryGatewayException ex) {
        log.warn("UserQueryGatewayException with msg {}", ex.getLocalizedMessage());
        return ResponseEntity.of(Optional.of(ex.getLocalizedMessage()))
                .status(HttpStatus.I_AM_A_TEAPOT)
                .build();
    }
}
