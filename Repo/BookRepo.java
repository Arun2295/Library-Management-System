package com.fitengineer.librarysystem.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fitengineer.librarysystem.entity.BookEntry;

public interface BookRepo extends MongoRepository<BookEntry,ObjectId>{

    @Query("{'_id': ?0}")
    BookEntry findbyid(ObjectId id);

    List<BookEntry> findByBookname(String bookname);
}
