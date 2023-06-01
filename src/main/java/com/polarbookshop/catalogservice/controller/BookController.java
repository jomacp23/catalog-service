package com.polarbookshop.catalogservice.controller;

import com.polarbookshop.catalogservice.domain.entity.Book;
import com.polarbookshop.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    //Get all the books in the catalog.
    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }
    
    //Get the book with the given ISBN
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }
    
    //Add a new book to the catalog
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }
    
    //Update the book with the given ISBN
    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
    
    //Delete a book with the given ISBN
    @DeleteMapping("{isbn}")
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
    
}
