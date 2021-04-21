package pl.dkiszka.bank.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.dkiszka.bank.models.User;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Builder
@Getter
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;
}
