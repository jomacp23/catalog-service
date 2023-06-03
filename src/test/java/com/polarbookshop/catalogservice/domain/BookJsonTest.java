package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {
    
    @Autowired
    private JacksonTester<Book> json;
    
    @Test
    protected void testSerialize() throws Exception {
        var now = Instant.now();
        var book = new Book(394L, "1234567890", "Title", "Author", 9.90, "Polarsophia", now, now, 21);
        var jsonContent = json.write(book);
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
        assertThat(jsonContent)
                .extractingJsonPathStringValue("@.publisher").isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathStringValue("@.createDate")
                .isEqualTo(book.createDate().toString());
        assertThat(jsonContent).extractingJsonPathStringValue("@.lastModifiedDate")
                .isEqualTo(book.lastModifiedDate().toString());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version")
                .isEqualTo(book.version());
    }

    @Test
    protected void testDeserialize() throws Exception {
        var instant = Instant.parse("2021-09-07T22:50:37.135029Z");
        var content = """
                {
                    "id": 394,
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "publisher": "Polarsophia",
                    "createDate": "2021-09-07T22:50:37.135029Z",
                    "lastModifiedDate": "2021-09-07T22:50:37.135029Z",
                    "version": 21
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book(394L, "1234567890", "Title", "Author", 9.90, "Polarsophia", instant, instant, 21));
    }
    
}
