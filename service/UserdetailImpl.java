package com.fitengineer.librarysystem.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.repository.UserRepo;

@Service
public class UserdetailImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntry user = userRepo.findByUsername(username)
            .orElseThrow(()-> new UsernameNotFoundException("user not found with username "+username));
            return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of(() -> "ROLE_"+ user.getRole()))
                .build();
    }




}
