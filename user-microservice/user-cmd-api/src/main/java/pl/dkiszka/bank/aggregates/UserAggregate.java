package pl.dkiszka.bank.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import pl.dkiszka.bank.commands.RegisterUserCommand;
import pl.dkiszka.bank.commands.RemoveUserCommand;
import pl.dkiszka.bank.commands.UpdateUserCommand;
import pl.dkiszka.bank.events.UserRegisteredEvent;
import pl.dkiszka.bank.events.UserRemovedEvent;
import pl.dkiszka.bank.events.UserUpdatedEvent;
import pl.dkiszka.bank.models.User;
import pl.dkiszka.bank.security.PasswordEncoder;
import pl.dkiszka.bank.security.PasswordEncoderImpl;

import java.util.UUID;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@Aggregate
class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        this.passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        this.passwordEncoder = new PasswordEncoderImpl();

        var newUser = command.getUser();
        newUser.setId(command.getId());
        var hashPassword = passwordEncoder.hashPassword(newUser.getAccount().getPassword());
        newUser.getAccount().setPassword(hashPassword);
        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);

    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());

        var hashPassword = passwordEncoder.hashPassword(updatedUser.getAccount().getPassword());
        updatedUser.getAccount().setPassword(hashPassword);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);

    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new RemoveUserCommand(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
