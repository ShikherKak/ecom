package com.ecom.userservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("users/hi")
    public void hello()
    {
        System.out.println("Hello");
    }

}
