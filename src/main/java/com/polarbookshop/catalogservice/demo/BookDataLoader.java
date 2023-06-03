package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.entity.Book;
import com.polarbookshop.catalogservice.domain.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("test-data")
public class BookDataLoader {
    
    private final BookRepository bookRepository;
    
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567890", "Northern Lights", "Lyra Silverstar", 9.99, "Publisher");
        var book2 = Book.of("1234567891", "Polar Journey", "Iorek Polarson", 10.99, "Publisher");
        bookRepository.saveAll(List.of(book1, book2));
        
    }
}
