package com.spring.jwt.demo.config;


import com.spring.jwt.demo.jwt.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/category/**",
            "/api/v1/food/**",
            "/api/v1/restaurant/**",
            "/api/v1/rating/**"
    };

    private static final String[] AUTH = {
            "/api/v1/category/**",
            "/api/v1/food/**",
            "/api/v1/restaurant/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(" /role/**").hasRole("ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/test").hasRole("USER")
                .and()
                .authorizeHttpRequests().requestMatchers(HttpMethod.POST, AUTH).hasAnyRole("MANAGER", "ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers(HttpMethod.PUT, AUTH).hasAnyRole("MANAGER", "ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers(HttpMethod.DELETE, AUTH).hasAnyRole("MANAGER", "ADMIN")
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setStatus(403);
                    }
                })
                .and()
                .build();
    }
}
