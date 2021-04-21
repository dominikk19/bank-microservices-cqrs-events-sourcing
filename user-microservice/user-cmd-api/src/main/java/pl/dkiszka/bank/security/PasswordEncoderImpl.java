package pl.dkiszka.bank.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(password);
    }
}
