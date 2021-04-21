package pl.dkiszka.bank.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
enum Role implements GrantedAuthority {
    READ_PRIVILEGE,
    WRITE_PRIVILEGE;

    @Override
    public String getAuthority() {
        return name();
    }
}
