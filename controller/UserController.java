package com.fitengineer.librarysystem.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<UserEntry>> getall(){
        List<UserEntry> user = userService.findall();
        return ResponseEntity.ok(user);
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserEntry user){
        userService.create(user);
        return ResponseEntity.ok("User created succussfully");
    }
    @GetMapping("/find/{username}")
    public ResponseEntity<UserEntry> getuserbyname(@PathVariable String username){
        UserEntry user = userService.getuserbyusername(username);
        if(user==null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(user);
    }
    

}
