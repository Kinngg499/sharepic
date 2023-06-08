package com.reskill.account.service.impl;

import com.reskill.account.dto.ErrorMessage;
import com.reskill.account.dto.request.LoginRequestDto;
import com.reskill.account.dto.request.RegistrationRequestDto;
import com.reskill.account.dto.response.LoginResponseDto;
import com.reskill.account.dto.response.SharePicResponseDto;
import com.reskill.account.entity.UserEntity;
import com.reskill.account.entity.UsersDetailEntity;
import com.reskill.account.exception.SharePicException;
import com.reskill.account.repo.UserDetailRepository;
import com.reskill.account.repo.UserRepository;
import com.reskill.account.service.AccountService;
import com.reskill.account.util.TokenGenerator;
import com.reskill.account.validator.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static com.reskill.account.exception.constants.ErrorDescription.INCORRECT_PASSWORD;
import static com.reskill.account.exception.constants.ErrorDescription.INCORRECT_USERNAME;
import static com.reskill.account.util.JsonConstants.*;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private RegistrationValidator registrationValidator;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public SharePicResponseDto register(RegistrationRequestDto registrationDto) {
        UserEntity user = new UserEntity();

        /*Dto Validation*/
        registrationValidator.usernameValidator(registrationDto.getUserName());
        registrationValidator.phoneNumberValidator(registrationDto.getPhoneNumber());
        registrationValidator.emailValidator(registrationDto.getEmail());

        user.setCountry(registrationDto.getCountry());
        user.setFirstname(registrationDto.getFirstName());
        user.setUsername(registrationDto.getUserName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(Base64.encodeBase64String(registrationDto.getPassword().getBytes(StandardCharsets.UTF_8)));
        user.setLastname(registrationDto.getLastName());
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user = userRepository.save(user);
        log.info("New user registered with id {}", user.getUserId());

        /*Update User Detail*/
        UsersDetailEntity userDetail = new UsersDetailEntity();
        userDetail.setUserEntity(user);
        userDetail.setFollowersCount(0);
        userDetail.setFollowingCount(0);
        userDetail = userDetailRepository.save(userDetail);
        log.info("New user detail added with user detail id {} for user {}", userDetail.getUsersDetailId(), user.getUsername());

        return new SharePicResponseDto(null, SUCCESS_CODE, null);
    }

    @Override
    public SharePicResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<UserEntity> user = userRepository
                .findByUsername(loginRequestDto.getUsername());
        LoginResponseDto loginResponse = new LoginResponseDto();
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            if (!Base64.encodeBase64String(loginRequestDto.getPassword().getBytes(StandardCharsets.UTF_8))
                    .equals(userEntity.getPassword())) {
                throw new SharePicException(new ErrorMessage(
                        SharePicException.class.getSimpleName(),
                        INCORRECT_PASSWORD,
                        null));
            }
            loginResponse
                    .setToken(tokenGenerator
                            .generateToken(loginRequestDto.getUsername(),
                                    userEntity.getEmail(),
                                    1000 * 6 * 60 * 60));
            loginResponse.setTokenType(ACCESS_TOKEN);
            loginResponse.setExpiryTimeInSeconds(ACCESS_TOKEN_EXPIRY_IN_SEC);
        } else {
            throw new SharePicException(new ErrorMessage(
                    SharePicException.class.getSimpleName(),
                    INCORRECT_USERNAME,
                    null));
        }
        return new SharePicResponseDto(loginResponse, SUCCESS_CODE, null);
    }
}
