package com.fitengineer.librarysystem.controller;
import com.fitengineer.librarysystem.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.fitengineer.librarysystem.repository.BookRepo;
import com.fitengineer.librarysystem.service.BookService;
import com.fitengineer.librarysystem.entity.BookEntry;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepo bookRepo;


    @Autowired
    private BookService bookService;


    BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    @GetMapping("/all")
    public ResponseEntity<List<BookEntry>> getallbooks(){
        List<BookEntry> books = bookService.getallbooks();
        return ResponseEntity.ok(books);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/Add")
    public ResponseEntity<String> addBook(@RequestBody BookEntry Book){
        bookService.addbook(Book);
        return ResponseEntity.ok("Book Added Successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookEntry> findbyid(@PathVariable ObjectId id){
        BookEntry book = bookService.findbyid(id);
        if(book==null){
            throw new RuntimeException("Book not found with id :"+id);
        }
        return ResponseEntity.ok(book);
    }
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<BookEntry> updateBook(@RequestBody BookEntry book,@PathVariable ObjectId id){
        BookEntry updatebook = bookService.updateBook(book, id);
        return ResponseEntity.ok(updatebook);
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable ObjectId id){
        bookService.deletebook(id);
        return ResponseEntity.ok("Book deleted succesfully");
    }
    @PostMapping("/rent/{userid}/books/{bookid}")
    public ResponseEntity<String> rentbook(@PathVariable ObjectId bookid,@PathVariable ObjectId userId){
        boolean sucess = bookService.rentbook(bookid, userId);
        if(sucess){
            return ResponseEntity.ok("Book rented Sucessfully");
        }else{
            return ResponseEntity.status(400).body("Rented not available");
        }

    }

        




    

}
