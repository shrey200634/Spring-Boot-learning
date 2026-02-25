package com.example.dokerExample.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {
    @GetMapping(value="/")
    public String ping(){
        return "Pong";

    }

}
