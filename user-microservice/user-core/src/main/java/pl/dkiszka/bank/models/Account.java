package pl.dkiszka.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 2, message = "username must have a min 2 char")
    @NotNull(message = "No username was specified")
    private String username;
    @Size(min = 8, message = "password must have a min 8 char")
    @NotNull(message = "No password was specified")
    private String password;
    @NotNull
    private List<Role> roles;
}
