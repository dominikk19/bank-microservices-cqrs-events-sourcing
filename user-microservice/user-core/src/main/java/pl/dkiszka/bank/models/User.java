package pl.dkiszka.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private Account account;
}
