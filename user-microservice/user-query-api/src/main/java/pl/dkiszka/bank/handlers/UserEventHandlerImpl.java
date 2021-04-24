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
        loggingPerformedOperation(event.getId(), "saved");
    }

    @EventHandler
    public void on(UserUpdatedEvent event) {
        log.info("caught the event UserUpdatedEvent");
        userRepository.save(event.getUser());
        loggingPerformedOperation(event.getId(), "updated");
    }

    @EventHandler
    public void on(UserRemovedEvent event) {
        log.info("caught the event UserRemovedEvent");
        userRepository.deleteById(event.getId());
        loggingPerformedOperation(event.getId(), "deleted");

    }

    private void loggingPerformedOperation(String userId, String operation){
        log.info("User by id: {} has been {}", userId, operation );
    }
}
