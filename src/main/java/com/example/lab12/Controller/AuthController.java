package com.example.lab12.Controller;

import com.example.lab12.Api.ApiResponse;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth-user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody MyUser myUser){

        authService.registerUser(myUser);
        return ResponseEntity.status(201).body(new ApiResponse("Registered successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(authService.getAllUsers()) ;
    }
}
