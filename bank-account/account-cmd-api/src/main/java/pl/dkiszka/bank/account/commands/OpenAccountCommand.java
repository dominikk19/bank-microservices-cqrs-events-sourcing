package pl.dkiszka.bank.account.commands;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import pl.dkiszka.bank.account.models.AccountType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Getter
@Setter
public class OpenAccountCommand {
    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "no account holder ID was supplied.")
    private String accountHolderId;

    @NotNull(message = "no account type was supplied.")
    private AccountType accountType;

    @Min(value = 50, message = "opening balance must be at least 50.")
    private BigDecimal openingBalance;

    public OpenAccountCommand(String id, String accountHolderId, AccountType accountType, BigDecimal openingBalance) {
        this.id = id;
        this.accountHolderId = accountHolderId;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
    }
}
