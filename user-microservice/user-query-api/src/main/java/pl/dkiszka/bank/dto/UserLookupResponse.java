package pl.dkiszka.bank.dto;

import lombok.Getter;
import pl.dkiszka.bank.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@Getter
public class UserLookupResponse extends BaseResponse {
    private final List<User> users;

    public UserLookupResponse(String message, List<User> users) {
        super(message);
        this.users =  users != null ? users : new ArrayList<>();
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(User user) {
        super(null);
        this.users = List.of(user);
    }
}
