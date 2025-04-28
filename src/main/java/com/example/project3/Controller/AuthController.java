package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Model.User;
import com.example.project3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid User user) {
        userService.register(user);
        return ResponseEntity.status(201).body(new ApiResponse("User registered successfully"));
    }
}
