package pl.dkiszka.bank.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.dkiszka.bank.models.User;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Builder
@Getter
public class UserUpdatedEvent {
    private String id;
    private User user;
}
