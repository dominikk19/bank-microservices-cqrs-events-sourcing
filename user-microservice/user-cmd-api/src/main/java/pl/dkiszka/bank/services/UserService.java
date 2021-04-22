package pl.dkiszka.bank.services;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.commands.RegisterUserCommand;
import pl.dkiszka.bank.dto.RegisterUserResponse;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final CommandGateway gateway;

    public RegisterUserResponse registerUser(RegisterUserCommand command) {
        command.setId(command.getId());

        return Try.of(() -> {
            gateway.sendAndWait(command);
            return new RegisterUserResponse("User successfully registered", command.getId());
        }).onFailure(exe -> {
            log.error("Error while processing register user fo id " + command.getId());
            throw new CommandGatewayException("Error while processing register user fo id " + command.getId());
        }).get();
    }
}
