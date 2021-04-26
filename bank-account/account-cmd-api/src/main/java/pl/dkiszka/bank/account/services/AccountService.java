package pl.dkiszka.bank.account.services;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.account.commands.CloseAccountCommand;
import pl.dkiszka.bank.account.commands.DepositFundsCommand;
import pl.dkiszka.bank.account.commands.OpenAccountCommand;
import pl.dkiszka.bank.account.commands.WithdrawFundsCommand;
import pl.dkiszka.bank.account.dto.BaseResponse;
import pl.dkiszka.bank.account.dto.OpenAccountResponse;

import java.util.UUID;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final CommandGateway commandGateway;

    public OpenAccountResponse openAccount(OpenAccountCommand command) {
        command.setId(UUID.randomUUID().toString());

        return Try.of(() -> {
            commandGateway.send(command);
            log.info("Send open account command");
            return new OpenAccountResponse("Account successfully opened", command.getId());
        }).onFailure(exe -> {
            log.error("Error while processing request to open account for id " + command.getId());
            throw new CommandGatewayException("Error while processing request to open account for id " + command.getId());
        }).get();
    }

    public BaseResponse closeAccount(String id) {
        return Try.of(() -> {
            commandGateway.send(new CloseAccountCommand(id));
            log.info("Send close account command");
            return new BaseResponse("Account successfully closed");
        }).onFailure(exe -> {
            log.error("Error while processing request to close account for id " + id);
            throw new CommandGatewayException("Error while processing closed account for id " + id);
        }).get();
    }


    public BaseResponse withdrawFunds(String id, WithdrawFundsCommand command) {
        return Try.of(() -> {
            command.setId(id);
            commandGateway.send(command).get();
            log.info("Withdrawal successfully completed");
            return new BaseResponse("Withdrawal successfully completed");
        }).onFailure(exe -> {
            log.error("Error while processing request to withdraw funds from account for id " + command.getId());
            throw new CommandGatewayException("Error while processing to withdraw funds from account for id " + command.getId());
        }).get();

    }

    public BaseResponse depositFunds(String id, DepositFundsCommand command) {
        return Try.of(() -> {
            command.setId(id);
            commandGateway.send(command);
            log.info("Funds successfully deposited");
            return new BaseResponse("Funds successfully deposited");
        }).onFailure(exe -> {
            log.error("Error while processing request to deposit funds from account for id " + command.getId());
            throw new CommandGatewayException("Error while processing request to deposit funds from open account for id " + command.getId());
        }).get();
    }
}
