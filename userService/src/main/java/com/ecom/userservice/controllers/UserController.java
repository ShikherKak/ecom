package com.ecom.userservice.controllers;

import com.ecom.userservice.dtos.LoginRequestDTO;
import com.ecom.userservice.dtos.SignUpRequestDTO;
import com.ecom.userservice.models.Token;
import com.ecom.userservice.models.User;
import com.ecom.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
    {
        String userName = signUpRequestDTO.getUserName();
        String email = signUpRequestDTO.getUserEmail();
        String password = signUpRequestDTO.getPassword();

        User newUser = userService.signUp(userName,email,password);
        return ResponseEntity.status(HttpStatus.CREATED).body("New user added with Email: "+newUser.getEmail());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userID)
    {
        userService.deleteUser(userID);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        Token token = userService.login(loginRequestDTO.getUserEmail(),loginRequestDTO.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("logged in successfully with token: "+token.getValue());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestBody String tokenValue)
    {
        userService.logout(tokenValue);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Logged Out");
    }


}
