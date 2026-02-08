package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Getter
    @Setter
    @AllArgsConstructor
    public class user{
        private String name ;

        private int age ;

        private String address;
    }

    private user user;

    public UserService(){   // singleton
        user = new user("shrey", 27, "Bangalore ,India");

    }

    public void logIn(){

        System.out.println("logged in user ");
    }

    public void logOut()throws Exception{
        System.out.println("logging user out ");
        throw new Exception("unable to logged out the user ");
    }



}
