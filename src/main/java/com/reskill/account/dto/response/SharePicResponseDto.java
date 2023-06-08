package com.reskill.account.dto.response;

import com.reskill.account.dto.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharePicResponseDto {
    private Object data;
    private Integer status;
    private ErrorMessage errorMessage;
}
