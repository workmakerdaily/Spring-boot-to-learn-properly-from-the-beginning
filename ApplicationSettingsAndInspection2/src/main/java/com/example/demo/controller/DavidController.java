package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.David;

@RestController
@RequestMapping("/david")
public class DavidController {
    private final David david;

    public DavidController(David david) {
        this.david = david;
    }

    @GetMapping
    David getDavid() {
        return david;
    }
}
