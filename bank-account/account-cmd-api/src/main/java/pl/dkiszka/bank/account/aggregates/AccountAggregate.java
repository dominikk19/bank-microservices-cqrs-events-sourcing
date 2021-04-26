package pl.dkiszka.bank.account.aggregates;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import pl.dkiszka.bank.account.commands.CloseAccountCommand;
import pl.dkiszka.bank.account.commands.DepositFundsCommand;
import pl.dkiszka.bank.account.commands.OpenAccountCommand;
import pl.dkiszka.bank.account.commands.WithdrawFundsCommand;
import pl.dkiszka.bank.account.events.AccountClosedEvent;
import pl.dkiszka.bank.account.events.AccountOpenedEvent;
import pl.dkiszka.bank.account.events.FundsDepositedEvent;
import pl.dkiszka.bank.account.events.FundsWithdrawnEvent;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Aggregate
@NoArgsConstructor
class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private BigDecimal balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        var event = AccountOpenedEvent
                .builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .creationDate(LocalDate.now())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHolderId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        var event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .balance(this.balance.add(command.getAmount()))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        var amount = command.getAmount();
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Withdrawal declined, insufficient funds");
        }

        var event = FundsWithdrawnEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .balance(this.balance.subtract(command.getAmount()))
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        this.balance = this.balance.subtract(event.getAmount());
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        var event = new AccountClosedEvent(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
