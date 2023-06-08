package com.reskill.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reskill.account.dto.request.RegistrationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

@Slf4j
public class Main {


    public static void main(String[] args) throws JsonProcessingException {
        RegistrationRequestDto dto = new RegistrationRequestDto();
        dto.setEmail("hee");
        log.info("{}",new ObjectMapper().writeValueAsString(dto));
        log.info("{}", String.valueOf(Base64.decodeBase64("S2FyYW5AMzIx".getBytes(StandardCharsets.UTF_8))));
    }
}
