package com.uade.api.ecommerce.ecommerce.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/API")
public class HelloWorldController {


    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping ("/hola")
    public String hola() { return "Hola mundo!"; }
}
