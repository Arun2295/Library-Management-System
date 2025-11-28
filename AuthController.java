package com.fitengineer.librarysystem.controller;
import com.fitengineer.librarysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.repository.UserRepo;
import com.fitengineer.librarysystem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserEntry user){
        String token = authService.login(user);
        return ResponseEntity.ok(token);


    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserEntry user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole()==null) user.setRole("user");
        userService.create(user);
        return ResponseEntity.ok("User register successfully");

    }

}
