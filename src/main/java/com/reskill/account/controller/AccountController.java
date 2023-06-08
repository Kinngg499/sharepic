package com.reskill.account.controller;

import com.reskill.account.dto.request.LoginRequestDto;
import com.reskill.account.dto.request.RegistrationRequestDto;
import com.reskill.account.dto.response.SharePicResponseDto;
import com.reskill.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.reskill.account.util.JsonConstants.IP_ADDRESS;

@RestController
@RequestMapping("/account")
@Validated
@Slf4j
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }


    @PostMapping("/register")
    public ResponseEntity<SharePicResponseDto> register(@RequestBody @Valid RegistrationRequestDto registrationDto,
                                                        HttpServletRequest httpRequest) {
        String ipAddress = httpRequest.getHeader(IP_ADDRESS);
        if (ipAddress == null) {
            ipAddress = httpRequest.getRemoteAddr();
        }
        log.info("New user registration request received from {}", ipAddress);
        SharePicResponseDto response = accountService.register(registrationDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<SharePicResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto,
                                                     HttpServletRequest httpRequest) {
        String ipAddress = httpRequest.getHeader(IP_ADDRESS);
        if (ipAddress == null) {
            ipAddress = httpRequest.getRemoteAddr();
        }
        log.info("Login request received from {}", ipAddress);
        SharePicResponseDto response = accountService.login(loginRequestDto);
        return ResponseEntity.ok(response);
    }
}
