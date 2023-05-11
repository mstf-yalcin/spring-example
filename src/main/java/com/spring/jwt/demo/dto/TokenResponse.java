package com.spring.jwt.demo.dto;

import lombok.*;

public record TokenResponse(String accessToken,
                            String refreshToken) {

}


//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class TokenResponse {
//
//    private String accessToken;
//
//    private String refreshToken;
//
//}
