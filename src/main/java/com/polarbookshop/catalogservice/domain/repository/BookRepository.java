package com.polarbookshop.catalogservice.domain.repository;

import com.polarbookshop.catalogservice.domain.entity.Book;

import java.util.Optional;

public interface BookRepository {
    
    Iterable<Book>findAll();
    
    Optional<Book> findByIsbn(String isbn);
    
    boolean existByIsbn(String isbn);
    
    Book save(Book book);
    
    void deleteByIsbn(String isbn);
    
}
