package pl.dkiszka.bank.security;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
public interface PasswordEncoder {
    String hashPassword(String password);
}
