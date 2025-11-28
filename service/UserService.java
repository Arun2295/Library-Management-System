package com.fitengineer.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntry create(UserEntry user){
        return userRepo.save(user);
    }

    public List<UserEntry> findall(){
        return userRepo.findAll();

    }
    public UserEntry getuserbyusername(String username){
        return userRepo.findByUsername(username).orElse(null);
    }

    

}
