package com.restapi.restapitest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "이름은 필수입니다")
    private String firstName;

    @NotBlank(message = "성은 필수입니다")
    private String lastName;

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "올바른 이메일 형식이어야 합니다")
    private String email;

    private String phoneNumber;
}
