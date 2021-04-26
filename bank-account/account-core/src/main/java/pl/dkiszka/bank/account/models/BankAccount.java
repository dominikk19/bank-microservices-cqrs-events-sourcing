package pl.dkiszka.bank.account.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BankAccount {
    @Id
    private String id;
    private String accountHolderId;
    private LocalDate creationDate;
    private AccountType accountType;
    @Setter
    private BigDecimal balance;


}
