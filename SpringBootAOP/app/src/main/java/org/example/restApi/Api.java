package org.example.restApi;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Api {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userLogin(){
        userService.logIn();
        return  "User login endpoint called successgully";
    }

}
