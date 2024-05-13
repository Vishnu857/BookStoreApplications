package com.bala.controller;

import com.bala.model.Books;
import com.bala.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bs;
    @Operation(summary = "Get all the books in the store", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<Books> getAllBooks() {
        return bs.getAllBooks();
    }


    @Operation(summary = "Get the book by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable("id") String id) {
        Optional<Books> optionalBook = bs.getById(id);
        return optionalBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a book to the DB", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add")
    public ResponseEntity<Books> addBook(@RequestBody Books book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bs.AddBooks(book));
    }
    @Operation(summary = "Delete a book by its ID", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String    > deleteBookById(@PathVariable("id") String id) {
        bs.DeleteBooks(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update details of the book", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/update")
    public ResponseEntity<Books> updateBook(@RequestBody Books book) {
            return ResponseEntity.ok(bs.UpdateBooks(book));

    }
    @Operation(summary = "Get the book details by its name", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/name/{bookname}")
    public Books findBookByName(@PathVariable("bookname") String name) {
        return bs.findByName(name);
    }

    @Operation(summary = "get book based on genre", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/genre/{genre}")
    public List<Books> findBooksByGenre(@PathVariable("genre") String genre) {
        return bs.findByGenre(genre);
    }

    @Operation(summary = "Get the book by author name", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/author/{name}")
    public List<Books> findBooksByAuthor(@PathVariable("name") String name) {
        return bs.findByAuthor(name);
    }
}
