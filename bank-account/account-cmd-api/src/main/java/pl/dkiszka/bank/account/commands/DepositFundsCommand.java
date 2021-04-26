package pl.dkiszka.bank.account.commands;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Getter
public class DepositFundsCommand {
    @TargetAggregateIdentifier
    @Setter
    private String id;

    @Min(value = 1, message = "the deposit amount must be greater than 0.")
    private BigDecimal amount;

    public DepositFundsCommand(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }
}

