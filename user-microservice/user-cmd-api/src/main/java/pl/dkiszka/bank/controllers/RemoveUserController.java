package pl.dkiszka.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dkiszka.bank.dto.BaseResponse;
import pl.dkiszka.bank.services.UserService;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
class RemoveUserController {
    private final UserService userService;

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    ResponseEntity<BaseResponse> registerUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
