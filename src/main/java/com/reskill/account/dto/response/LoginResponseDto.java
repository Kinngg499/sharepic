package com.reskill.account.dto.response;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String token;
    private String tokenType;
    private Integer expiryTimeInSeconds;
}
