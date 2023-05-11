package com.spring.jwt.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record RefreshTokenRequestDto(@NotBlank String refreshToken) {
}


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class RefreshTokenRequestDto {
//
//    @NotBlank
//    private String refreshToken;
//}
