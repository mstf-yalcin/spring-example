package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomBaseController {
    protected <T> ResponseEntity<Object> createResponse(CustomResponseDto<T> response) {
        if (response.statusCode() == HttpStatus.NO_CONTENT.value()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.statusCode()));
        }
    }

}
