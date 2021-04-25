package pl.dkiszka.bank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.dkiszka.bank.models.User;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 25.04.2021
 */
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'account.username' : ?0}")
    Optional<User> findAllByUsername(String username);
}
