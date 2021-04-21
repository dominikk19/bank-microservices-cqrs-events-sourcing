package pl.dkiszka.bank.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Getter
public class UserRemovedEvent {
    private String id;

}
