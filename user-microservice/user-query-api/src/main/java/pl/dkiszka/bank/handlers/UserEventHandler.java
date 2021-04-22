package pl.dkiszka.bank.handlers;

import pl.dkiszka.bank.events.UserRegisteredEvent;
import pl.dkiszka.bank.events.UserRemovedEvent;
import pl.dkiszka.bank.events.UserUpdatedEvent;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
public interface UserEventHandler {
    void consume(UserRegisteredEvent event);

    void consume(UserUpdatedEvent event);

    void consume(UserRemovedEvent event);

}
