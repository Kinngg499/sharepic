package com.reskill.account.exception;

import com.reskill.account.dto.ErrorMessage;
import lombok.Data;

@Data
public class SharePicException extends RuntimeException {

    private ErrorMessage errorMessage;

    public SharePicException(ErrorMessage errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public SharePicException(String message, ErrorMessage errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

}
