package pl.dkiszka.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Getter
@Setter
public class Account {
    private String username;
    private String password;
    private List<Role> roles;
}
