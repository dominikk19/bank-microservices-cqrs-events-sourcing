package pl.dkiszka.bank.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.dto.UserLookupResponse;
import pl.dkiszka.bank.queries.FindAllUserQuery;
import pl.dkiszka.bank.queries.FindUserByIdQuery;
import pl.dkiszka.bank.queries.SearchUsersQuery;
import pl.dkiszka.bank.repositories.UserRepository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryHandler {
    private final UserRepository userRepository;

    @QueryHandler
    public UserLookupResponse getAllUsers(FindAllUserQuery query) {
        loggingPerformedOperation("getAllUsers");
        return new UserLookupResponse(userRepository.findAll());
    }

    @QueryHandler
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        loggingPerformedOperation("getUserById for id "+query.getId());
        return userRepository.findById(query.getId())
                .map(UserLookupResponse::new)
                .orElse(new UserLookupResponse(String.format("User by id %s not found", query.getId()), null));
    }

    @QueryHandler
    public UserLookupResponse searchUsersByFilter(SearchUsersQuery query) {
        loggingPerformedOperation("searchUsersByFilter");
        return new UserLookupResponse(userRepository.findByFilterRegex(query.getFilter()));
    }

    private void loggingPerformedOperation(String method){
        log.info("UserQueryHandler method {} ", method);
    }
}
