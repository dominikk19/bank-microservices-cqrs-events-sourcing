package pl.dkiszka.bank.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.dkiszka.bank.models.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    private String id;
    @Valid
    @NotNull(message = "No user details were supplied")
    private User user;

}
