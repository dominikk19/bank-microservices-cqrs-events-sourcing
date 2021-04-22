package pl.dkiszka.bank.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.dkiszka.bank.models.User;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
public interface UserRepository extends MongoRepository<User, String> {
}
