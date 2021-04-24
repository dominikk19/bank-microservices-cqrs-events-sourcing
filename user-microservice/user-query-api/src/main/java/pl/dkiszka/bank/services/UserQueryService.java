package pl.dkiszka.bank.services;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.dto.UserLookupResponse;
import pl.dkiszka.bank.queries.FindAllUserQuery;
import pl.dkiszka.bank.queries.FindUserByIdQuery;
import pl.dkiszka.bank.queries.SearchUsersQuery;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {
    private final QueryGateway gateway;

    public UserLookupResponse getAllUsers() {
        return Try.of(() -> {
            log.info("Send query for all users");
            return gateway.query(new FindAllUserQuery(), ResponseTypes.instanceOf(UserLookupResponse.class)).join();
        }).onFailure(exe -> {
            log.error("Failed to complete get all users request");
            throw new UserQueryGatewayException("Failed to complete get all users request");
        }).get();
    }

    public UserLookupResponse getUserById(String id) {

        return Try.of(() -> {
            log.info("Send query for user by id");
            return gateway.query(new FindUserByIdQuery(id), ResponseTypes.instanceOf(UserLookupResponse.class)).join();
        }).onFailure(exe -> {
            log.error("Failed to complete get user by id: {} request", id);
            throw new UserQueryGatewayException(String.format("Failed to complete get user by id: %s request", id));
        }).get();
    }

    public UserLookupResponse findUsersByFilter(String filter) {
        return Try.of(() -> {
            log.info("Send query for search users by filter");
            return gateway.query(new SearchUsersQuery(filter), ResponseTypes.instanceOf(UserLookupResponse.class)).join();
        }).onFailure(exe -> {
            log.error("Failed to complete get users by filter: {} request", filter);
            throw new UserQueryGatewayException(String.format("Failed to complete get users by filter: %s request", filter));
        }).get();
    }
}
