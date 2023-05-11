package com.spring.jwt.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomResponseDto<T>(T data,
                                   int statusCode,
                                   List<String> errors) {
    public static <T> CustomResponseDto<T> Success(int statusCode, T data) {
        CustomResponseDto<T> responseDto = new CustomResponseDto<T>(data,statusCode,null);
        return responseDto;
    }

    public static <T> CustomResponseDto<T> Success(int statusCode) {
        CustomResponseDto<T> responseDto = new CustomResponseDto<T>(null,statusCode,null);
        return responseDto;
    }

    public static <T> CustomResponseDto<T> Fail(int statusCode, List<String> errors) {
        CustomResponseDto<T> responseDto = new CustomResponseDto<T>(null,statusCode,errors);
        return responseDto;
    }

    public static <T> CustomResponseDto<T> Fail(int statusCode, String errors) {

        List<String> error = new ArrayList<>();
        error.add(errors);
        CustomResponseDto<T> responseDto = new CustomResponseDto<T>(null,statusCode,error);
        return responseDto;
    }

}




//*********With lombok


//package com.spring.jwt.demo.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import lombok.*;


//@Getter
//@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class CustomResponseDto<T> {
//
//
//    private T data;
//
//    private int statusCode;
//    private List<String> errors;
//
////    private Map<String,String> errorsMap;
//
//    public static <T> CustomResponseDto<T> Success(int statusCode, T data) {
//        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
//        responseDto.setData(data);
//        responseDto.setStatusCode(statusCode);
//        return responseDto;
//    }
//
//    public static <T> CustomResponseDto<T> Success(int statusCode) {
//        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
//        responseDto.setStatusCode(statusCode);
//        return responseDto;
//    }
//
//    public static <T> CustomResponseDto<T> Fail(int statusCode, List<String> errors) {
//        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
//        responseDto.setStatusCode(statusCode);
//        responseDto.setErrors(errors);
//        return responseDto;
//    }
//
//    public static <T> CustomResponseDto<T> Fail(int statusCode, String errors) {
//        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
//        responseDto.setStatusCode(statusCode);
//
//        List<String> error = new ArrayList<>();
//        error.add(errors);
//        responseDto.setErrors(error);
//        return responseDto;
//    }
//
////    public static <T> CustomResponseDto<T> Fail(int statusCode, Map<String,String> errors) {
////        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
////        responseDto.setStatusCode(statusCode);
////        responseDto.setErrorsMap(errors);
////        return responseDto;
////    }
//
//
//
////    private T data;
////
////    private int statusCode;
////    private List<ErrorViewModel> errors;
////
////    public static <T> CustomResponseDto<T> Success(int statusCode, T data) {
////        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
////        responseDto.setData(data);
////        responseDto.setStatusCode(statusCode);
////        return responseDto;
////    }
////
////    public static <T> CustomResponseDto<T> Success(int statusCode) {
////        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
////        responseDto.setStatusCode(statusCode);
////        return responseDto;
////    }
////
////    public static <T> CustomResponseDto<T> Fail(int statusCode, List<ErrorViewModel> errors) {
////        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
////        responseDto.setStatusCode(statusCode);
////        responseDto.setErrors(errors);
////        return responseDto;
////    }
////
////    public static <T> CustomResponseDto<T> Fail(int statusCode, ErrorViewModel errors) {
////        CustomResponseDto<T> responseDto = new CustomResponseDto<T>();
////        responseDto.setStatusCode(statusCode);
////
////        List<ErrorViewModel> error = new ArrayList<>();
////        error.add(errors);
////        responseDto.setErrors(error);
////        return responseDto;
////    }
//
//}
