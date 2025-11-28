package com.fitengineer.librarysystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fitengineer.librarysystem.entity.Rent;
import com.mongodb.client.MongoDatabase;

public interface RentRepo extends MongoRepository<Rent,ObjectId>{
    List<Rent> findByRentDateBeforeAndEmailSent(LocalDateTime date,boolean emailSent);

}
