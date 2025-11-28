package com.fitengineer.librarysystem.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fitengineer.librarysystem.entity.UserEntry;


public interface UserRepo extends MongoRepository<UserEntry,String> {

    Optional<UserEntry> findByUsername(String username);


    

}
