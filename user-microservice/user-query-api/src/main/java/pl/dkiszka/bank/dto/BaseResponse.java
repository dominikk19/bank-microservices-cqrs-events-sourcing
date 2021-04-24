package pl.dkiszka.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@AllArgsConstructor
@Getter
public class BaseResponse {
    private String message;
}
