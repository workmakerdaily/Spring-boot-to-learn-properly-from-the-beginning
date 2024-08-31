package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// @Value 사용
// @RestController
// @RequestMapping("/greeting")
// public class GreetingController {
//     // @Value가 멤버 변수 name에 적용
//     @Value("${greeting-name: ItalyFood}")
//     private String name;

import com.example.demo.entity.Greeting;

//     // food의 @Value 매개변수에 기본값을 명시함
//     @Value("${greeting-food: ${greeting-name} is very delicious}")
//     private String food;

//     @GetMapping
//     String getGreeting() {
//         return name;
//     }

//     @GetMapping("/food")
//     String getNameFood() {
//         return food;
//     }
// }
@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final Greeting greeting;
    
    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    String getGreeting() {
        return greeting.getName();
    }

    @GetMapping("/food")
    String getNameFood() {
        return greeting.getFood();
    }
}