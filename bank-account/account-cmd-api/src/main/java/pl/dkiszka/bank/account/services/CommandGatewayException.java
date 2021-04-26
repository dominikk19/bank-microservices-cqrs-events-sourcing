package pl.dkiszka.bank.account.services;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
public class CommandGatewayException extends RuntimeException {
    public CommandGatewayException(String message) {
        super(message);
    }
}
