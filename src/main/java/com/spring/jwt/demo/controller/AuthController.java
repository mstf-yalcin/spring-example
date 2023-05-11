package com.spring.jwt.demo.controller;


import com.spring.jwt.demo.dto.RefreshTokenRequestDto;
import com.spring.jwt.demo.dto.UserLoginDto;
import com.spring.jwt.demo.dto.UserRegisterDto;
import com.spring.jwt.demo.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@SecurityRequirements(value = {})
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<CustomResponseDto<TokenResponse>> register(@RequestBody UserDto userDto) {
////        return new ResponseEntity<>(authService.register(userDto), HttpStatus.CREATED);
//        return ResponseEntity.created(URI.create("/register" + userDto.getEmail())).body(authService.register(userDto));
//    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid  @RequestBody UserRegisterDto userRegisterDto) {
//        return new ResponseEntity<>(authService.register(userDto), HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/register" + userRegisterDto.email())).body(authService.register(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid  @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authService.login(userLoginDto));
    }

    @PostMapping("/refrehTokenlogin")
    public ResponseEntity refrehTokenlogin(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ResponseEntity.ok(authService.refreshTokenLogin(refreshTokenRequestDto));
    }

}
