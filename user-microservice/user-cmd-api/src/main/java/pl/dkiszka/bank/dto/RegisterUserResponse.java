package pl.dkiszka.bank.dto;

import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 21.04.2021
 */
@Getter
public class RegisterUserResponse extends BaseResponse {

    private String id;

    public RegisterUserResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
