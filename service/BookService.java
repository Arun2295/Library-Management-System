package com.fitengineer.librarysystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitengineer.librarysystem.entity.BookEntry;
import com.fitengineer.librarysystem.entity.Rent;
import com.fitengineer.librarysystem.entity.UserEntry;
import com.fitengineer.librarysystem.repository.BookRepo;
import com.fitengineer.librarysystem.repository.RentRepo;
import com.fitengineer.librarysystem.repository.UserRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RentRepo rentRepo;

    //only admin can do
    public BookEntry addbook(BookEntry book){
        return bookRepo.save(book);
    }

    public List<BookEntry> getallbooks(){
        return bookRepo.findAll();
    }
    public void deletebook(ObjectId id){
        BookEntry book = bookRepo.findbyid(id);
        if(book !=null){
            bookRepo.delete(book);
        }else{
            throw new RuntimeException("Book not found");
        }
    }

    public BookEntry findbyid(ObjectId id){
        return bookRepo.findbyid(id);
    }

    //only admin can do
    public BookEntry updateBook(BookEntry book,ObjectId id){
        BookEntry existingbook = bookRepo.findbyid(id);
        if(existingbook != null){
            existingbook.setBookname(book.getBookname());
            existingbook.setAuthor(book.getAuthor());
            existingbook.setQuantity(book.getQuantity());
            return bookRepo.save(existingbook);

        }else{
            throw new RuntimeException("Book not found with id "+id);
        }

    }
    public boolean rentbook(ObjectId bookid,ObjectId userid){
        BookEntry book = bookRepo.findbyid(bookid);
        if(book ==  null || book.getQuantity() <= 0){
            return false;
            

        }
        UserEntry user = userRepo.findById(userid.toHexString()).orElse(null);
        if(user==null){
            return false;
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        Rent rent = new Rent();
        rent.setBookId(bookid);
        rent.setBookname(book.getBookname());
        rent.setUserId(userid);
        rent.setRentDate(LocalDateTime.now());
        rent.setEmailSent(false);
        rentRepo.save(rent);

        return true;


    }

    public List<BookEntry> findbyBookname(String bookname){
        return bookRepo.findByBookname(bookname);
    }
    
    

}
