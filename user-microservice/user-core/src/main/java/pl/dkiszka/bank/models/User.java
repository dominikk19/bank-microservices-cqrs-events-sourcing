package pl.dkiszka.bank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    @NotEmpty(message = "firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "lastname is mandatory")
    private String lastname;
    @Email
    private String emailAddress;
    @NotNull(message = "provide account credentials")
    private Account account;
}
