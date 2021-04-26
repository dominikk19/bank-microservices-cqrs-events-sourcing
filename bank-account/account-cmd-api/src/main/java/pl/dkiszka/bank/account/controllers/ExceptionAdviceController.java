package pl.dkiszka.bank.account.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.dkiszka.bank.account.services.CommandGatewayException;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@ControllerAdvice
class ExceptionAdviceController {

    @ExceptionHandler(value = {CommandGatewayException.class})
    ResponseEntity<Void> commandGatewayException(CommandGatewayException exec) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                .build();
    }
}
