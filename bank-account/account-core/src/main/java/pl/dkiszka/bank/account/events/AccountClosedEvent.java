package pl.dkiszka.bank.account.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@AllArgsConstructor
@Getter
public class AccountClosedEvent {
    private String id;
}
