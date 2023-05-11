package com.spring.jwt.demo.exception;


import com.spring.jwt.demo.dto.CustomResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
        ArrayList<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getField() + ": " + error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return new ResponseEntity<>(CustomResponseDto.Fail(400, errors), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex)
//    {
//        List<String> errors = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
////            String fieldName = ((FieldError) error).getField();
//            String errorMessage = ((FieldError) error).getField() + ": " + error.getDefaultMessage();
//            errors.add(errorMessage);
//        });
//        return new ResponseEntity<>(CustomResponseDto.Fail(400, errors), HttpStatus.BAD_REQUEST);
//
//    }


    //    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateFieldException.class)
    public ResponseEntity<CustomResponseDto> duplicateException(DuplicateFieldException ex) {

        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.CONFLICT.value(), errors), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomResponseDto> userNameNotFoundException(UsernameNotFoundException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomResponseDto> notFoundExcepiton(NotFoundException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.NOT_FOUND.value(), errors), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void unAuthorizeException(UnAuthorizedException ex) {

    }


    //Usernotfound
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<CustomResponseDto> test(InternalAuthenticationServiceException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<CustomResponseDto> malformedJwtException(MalformedJwtException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<CustomResponseDto>expiredJwtException(ExpiredJwtException ex) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDto> handleException(Exception ex) {

        ArrayList<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        if (ex instanceof NullPointerException)
            return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);

//      return   ResponseEntity.status(401).body(CustomResponseDto.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors));
        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<CustomResponseDto<ErrorViewModel>> userNotFoundException(UsernameNotFoundException usernameNotFoundException) {
//        ErrorViewModel errorResponse = new (usernameNotFoundException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return new ResponseEntity<>(CustomResponseDto.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),errorResponse);
//    }

    //    @Override
//    protected ResponseEntity<CustomResponseDto<ErrorViewModel>> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        int statusCode = status.value();
//        return new ResponseEntity<>(CustomResponseDto.Fail(status.value(),
//                ErrorViewModel.builder().errorMessage(ex.getMessage()).build()),HttpStatus.valueOf(status.value()));
//
//    }
}
