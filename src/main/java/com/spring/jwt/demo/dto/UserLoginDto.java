package com.spring.jwt.demo.dto;

import com.spring.jwt.demo.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


public record UserLoginDto(@NotBlank(message = "{validation.constraints.NotNull.message}")
                           @Email
                           String email,
                           @NotBlank(message = "{validation.constraints.NotNull.message}")
                           String password) {
}


//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class UserLoginDto {
//    @UniqueEmail
//    @NotBlank(message = "{validation.constraints.NotNull.message}")
//    @Email
//    private String email;
//
//    @NotBlank(message = "{validation.constraints.NotNull.message}")
//    private String password;
//
//}

