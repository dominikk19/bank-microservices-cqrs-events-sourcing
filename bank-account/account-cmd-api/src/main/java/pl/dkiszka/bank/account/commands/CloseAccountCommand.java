package pl.dkiszka.bank.account.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@AllArgsConstructor
@Getter
public class CloseAccountCommand {
    @TargetAggregateIdentifier
    private String id;
}
