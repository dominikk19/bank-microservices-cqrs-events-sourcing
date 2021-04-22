package pl.dkiszka.bank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.dkiszka.bank.services.CommandGatewayException;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@ControllerAdvice
class ExceptionAdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CommandGatewayException.class})
    ResponseEntity<Void> commandGatewayException(CommandGatewayException exec) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

}
