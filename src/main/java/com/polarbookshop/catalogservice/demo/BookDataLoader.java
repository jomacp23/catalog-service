package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.entity.Book;
import com.polarbookshop.catalogservice.domain.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("test-data")
public class BookDataLoader {
    
    private final BookRepository bookRepository;
    
    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567890", "Northern Lights", "Lyra Silverstar", 9.99);
        bookRepository.save(book1);
        var book2 = new Book("1234567891", "Polar Journey", "Iorek Polarson", 10.99);
        bookRepository.save(book2);
        
    }
}
