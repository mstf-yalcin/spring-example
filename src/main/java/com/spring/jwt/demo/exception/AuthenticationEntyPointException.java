//package com.spring.jwt.demo.exception;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.io.IOException;
//
//@ControllerAdvice
//public class AuthenticationEntyPointException implements AuthenticationEntryPoint {
//
//    // 401
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//    }
//
//
//    // 403
//    @ExceptionHandler(value = {AccessDeniedException.class})
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AccessDeniedException accessDeniedException) throws IOException {
//
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
//    }
//
//
//    // 500
//    @ExceptionHandler(value = {Exception.class})
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         Exception exception) throws IOException {
//
//        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
//    }
//
//
//}
