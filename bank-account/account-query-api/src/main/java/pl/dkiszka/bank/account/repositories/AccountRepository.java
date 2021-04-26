package pl.dkiszka.bank.account.repositories;

import org.springframework.data.repository.Repository;
import pl.dkiszka.bank.account.models.BankAccount;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
public interface AccountRepository extends Repository<BankAccount, String> {
    BankAccount save(BankAccount bankAccount);

    Optional<BankAccount> findById(String id);

    void deleteById(String id);
}
