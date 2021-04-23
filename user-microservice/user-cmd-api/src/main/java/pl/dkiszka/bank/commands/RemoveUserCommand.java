package pl.dkiszka.bank.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Getter
public class RemoveUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
