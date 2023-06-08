package com.reskill.account.util;

public class JsonConstants {

    private JsonConstants() {
    }

    public static final Integer SUCCESS_CODE = 0;
    public static final Integer FAILURE_CODE = 1;
    public static final String IP_ADDRESS = "X-FORWARDED-FOR";
    public static final String ACCESS_TOKEN = "Access Token";
    public static final Integer ACCESS_TOKEN_EXPIRY_IN_SEC = 21600;
}
