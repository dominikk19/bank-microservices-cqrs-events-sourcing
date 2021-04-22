package pl.dkiszka.bank.handlers;

import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import pl.dkiszka.bank.events.UserRegisteredEvent;
import pl.dkiszka.bank.events.UserRemovedEvent;
import pl.dkiszka.bank.events.UserUpdatedEvent;
import pl.dkiszka.bank.repositories.UserRepository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@RequiredArgsConstructor
@Component
@ProcessingGroup("user-group")
class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void consume(UserRegisteredEvent event) {
        userRepository.save(event.getUser());

    }

    @EventHandler
    @Override
    public void consume(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void consume(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
