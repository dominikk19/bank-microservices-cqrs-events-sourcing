package pl.dkiszka.bank.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 24.04.2021
 */
@AllArgsConstructor
@Getter
public class FindUserByIdQuery {
    private String id;
}
