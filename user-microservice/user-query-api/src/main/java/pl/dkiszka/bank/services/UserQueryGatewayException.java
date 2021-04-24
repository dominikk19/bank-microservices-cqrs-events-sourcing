package pl.dkiszka.bank.services;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
public class UserQueryGatewayException extends RuntimeException {
    public UserQueryGatewayException(String msg) {
        super(msg);
    }
}
