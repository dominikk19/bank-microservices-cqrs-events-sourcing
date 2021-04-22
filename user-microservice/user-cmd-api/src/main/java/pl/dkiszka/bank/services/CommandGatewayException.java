package pl.dkiszka.bank.services;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
public class CommandGatewayException extends RuntimeException {
    public CommandGatewayException(String message) {
        super(message);
    }
}
