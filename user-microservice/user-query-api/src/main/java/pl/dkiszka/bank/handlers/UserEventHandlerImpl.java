package pl.dkiszka.bank.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@ProcessingGroup("user-group")
class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    public void on(UserRegisteredEvent event) {
        log.info("caught the event UserRegisteredEvent");
        userRepository.save(event.getUser());
        log.info("User by id: " + event.getUser().getId() + " has been saved");
    }

    @EventHandler
    public void on(UserUpdatedEvent event) {
        log.info("caught the event UserUpdatedEvent");
        userRepository.save(event.getUser());
    }

    @EventHandler
    public void on(UserRemovedEvent event) {
        log.info("caught the event UserRemovedEvent");
        userRepository.deleteById(event.getId());
    }
}
