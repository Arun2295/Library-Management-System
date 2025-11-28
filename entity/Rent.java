package com.fitengineer.librarysystem.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rents")
public class Rent {

    @Id
    private ObjectId id;

    private ObjectId userId;
    private ObjectId bookId;

    private String bookname;
    private LocalDateTime rentDate;
    private boolean emailSent = false;


}
