package com.reskill.account.controller.advice;

import com.reskill.account.dto.ErrorMessage;
import com.reskill.account.exception.SharePicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class AccountControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        ErrorMessage errorMessage = new ErrorMessage();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        errorMessage.setErrorFields(errorMap);
        errorMessage.setExceptionType(MethodArgumentNotValidException.class.getSimpleName());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SharePicException.class)
    public ResponseEntity<?> handleSharePicException(SharePicException exception) {
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
