package com.spring.jwt.demo.service;

import com.spring.jwt.demo.dto.*;
import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.Role;
import com.spring.jwt.demo.entity.User;
import com.spring.jwt.demo.jwt.JwtService;
import com.spring.jwt.demo.jwt.RefreshTokenService;
import com.spring.jwt.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, RoleService roleService, JwtService jwtService, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }


    public CustomResponseDto<TokenResponse> register(UserRegisterDto userRegisterDto) {
        List<Role> roleList = new ArrayList<>();
        Role role = roleService.getRole(Enum_Role.ROLE_USER);
        roleList.add(role);


        //@UniqueEmail constraint
//        if (userRepository.findByEmail(userDto.getEmail()).isPresent())
//            throw new DuplicateFieldException("Duplicate data: " + userDto.getEmail());

        User user = User.builder()
                .email(userRegisterDto.email())
                .password(passwordEncoder.encode(userRegisterDto.password()))
                .ratings(new HashSet<>())
                .roleList(roleList).build();


        userRepository.save(user);

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

//        return  TokenResponse.builder()
//                .accessToken(accessToken).refreshToken(refreshToken).build();

        return CustomResponseDto.Success(201, new TokenResponse(accessToken, refreshToken.getToken()));

    }


    public CustomResponseDto<TokenResponse> login(UserLoginDto userLoginDto) {


        //TO DO: delete user tokens

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password()));
//        if(authentication.isAuthenticated())

        User user = userRepository.findByEmail(userLoginDto.email()).orElseThrow(() -> new UsernameNotFoundException("User not found"));


        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

//        return TokenResponse.builder()
//                .accessToken(accessToken).refreshToken(refreshToken).build();
        return CustomResponseDto.Success(200, new TokenResponse(accessToken, refreshToken.getToken()));
    }


    public CustomResponseDto<TokenResponse> refreshTokenLogin(RefreshTokenRequestDto refreshTokenRequestDto) {
        var newRefreshToken = jwtService.refreshTokenLogin(refreshTokenRequestDto);
        var accessToken = jwtService.generateToken(newRefreshToken.getUser());

        return CustomResponseDto.Success(200, new TokenResponse(accessToken, newRefreshToken.getToken()));
    }


}
