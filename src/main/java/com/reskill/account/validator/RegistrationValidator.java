package com.reskill.account.validator;

import com.reskill.account.dto.ErrorMessage;
import com.reskill.account.entity.UserEntity;
import com.reskill.account.exception.SharePicException;
import com.reskill.account.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.reskill.account.exception.constants.ErrorDescription.*;


@Component
public class RegistrationValidator {

    @Autowired
    private UserRepository userRepository;

    public void usernameValidator(String username) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(username);
        existingUser.ifPresent(
                user -> {
                    throw new SharePicException(new ErrorMessage(
                            SharePicException.class.getSimpleName(),
                            USERNAME_ALREADY_EXISTS,
                            null));
                });
    }

    public void phoneNumberValidator(String phoneNumber) {
        Optional<UserEntity> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        existingUser.ifPresent(
                user -> {
                    throw new SharePicException(new ErrorMessage(
                            SharePicException.class.getSimpleName(),
                            PHONE_NUMBER_ALREADY_EXISTS,
                            null));
                });
    }

    public void emailValidator(String email) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(email);
        existingUser.ifPresent(
                user -> {
                    throw new SharePicException(new ErrorMessage(
                            SharePicException.class.getSimpleName(),
                            EMAIL_ALREADY_EXISTS,
                            null));
                });
    }
}
