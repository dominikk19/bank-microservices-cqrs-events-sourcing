package pl.dkiszka.bank.account.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@AllArgsConstructor
@Getter
@Builder
public class FundsWithdrawnEvent {
    private String id;
    private BigDecimal amount;
    private BigDecimal balance;
}
