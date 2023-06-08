package com.reskill.account.service;

import com.reskill.account.dto.request.LoginRequestDto;
import com.reskill.account.dto.request.RegistrationRequestDto;
import com.reskill.account.dto.response.SharePicResponseDto;

public interface AccountService {
    SharePicResponseDto register(RegistrationRequestDto registrationDto);

    SharePicResponseDto login(LoginRequestDto loginRequestDto);
}
