package com.fitengineer.librarysystem.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookEntry {

    @Id
    private ObjectId id;
    private String bookname;
    private String author;
    private int quantity;
  

}
