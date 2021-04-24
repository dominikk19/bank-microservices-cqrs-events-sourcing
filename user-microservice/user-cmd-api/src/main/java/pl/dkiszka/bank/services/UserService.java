package pl.dkiszka.bank.services;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.commands.RegisterUserCommand;
import pl.dkiszka.bank.commands.RemoveUserCommand;
import pl.dkiszka.bank.commands.UpdateUserCommand;
import pl.dkiszka.bank.dto.BaseResponse;
import pl.dkiszka.bank.dto.RegisterUserResponse;

import java.util.UUID;

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
        command.setId(UUID.randomUUID().toString());

        return Try.of(() -> {
            gateway.send(command);
            log.info("Send register user command");
            return new RegisterUserResponse("User successfully registered", command.getId());
        }).onFailure(exe -> {
            log.error("Error while processing register user for id " + command.getId());
            throw new CommandGatewayException("Error while processing register user for id " + command.getId());
        }).get();
    }

    public BaseResponse updateUser(String id, UpdateUserCommand command) {
        command.setId(id);
        return Try.of(() -> {
            gateway.send(command);
            log.info("Send update user command");
            return new BaseResponse("User successfully updated");
        }).onFailure(exe -> {
            log.error("Error while processing update user for id " + command.getId());
            throw new CommandGatewayException("Error while processing update user for id " + command.getId());
        }).get();
    }

    public BaseResponse deleteUser(String id) {
        return Try.of(() -> {
            gateway.send(new RemoveUserCommand(id));
            log.info("Send delete user command");
            return new BaseResponse("User successfully deleted");
        }).onFailure(exe -> {
            log.error("Error while processing deleted user for id " + id);
            throw new CommandGatewayException("Error while processing deleted user for id " + id);
        }).get();
    }
}
