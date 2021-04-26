package pl.dkiszka.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 26.04.2021
 */
@AllArgsConstructor
@Getter
public class BaseResponse {
    private String message;
}
