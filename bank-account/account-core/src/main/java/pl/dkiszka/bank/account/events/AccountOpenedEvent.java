package pl.dkiszka.bank.account.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.dkiszka.bank.account.models.AccountType;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@AllArgsConstructor
@Getter
@Builder
public class AccountOpenedEvent {
    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private LocalDate creationDate;
    private BigDecimal openingBalance;
}
