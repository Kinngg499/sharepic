package com.reskill.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String exceptionType;
    private String errorDescription;
    private Map<String, String> errorFields;
}
