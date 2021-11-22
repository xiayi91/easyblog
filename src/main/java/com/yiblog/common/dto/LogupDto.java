package com.yiblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LogupDto extends LoginDto implements Serializable {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String avatar;

}
