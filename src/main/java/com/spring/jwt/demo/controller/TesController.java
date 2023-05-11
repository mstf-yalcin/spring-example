package com.spring.jwt.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")

public class TesController extends CustomBaseController {

    @GetMapping
    public ResponseEntity<?> get()
    {
        return ResponseEntity.ok("asd");
    }

}
