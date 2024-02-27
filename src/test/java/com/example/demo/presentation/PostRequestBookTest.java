package com.example.demo.presentation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostRequestBookTest {

    @Test
    void titleがnullの場合() {
        assertThrows(BookValidationException.class, () -> {
            new PostRequestBook(null, "Author", "Publisher", 100);
        });
    }

    @Test
    void authorがnullの場合() {
        assertThrows(BookValidationException.class, () -> {
            new PostRequestBook("title", null, "Publisher", 100);
        });
    }

    @Test
    void publisherがnullの場合() {
        assertThrows(BookValidationException.class, () -> {
            new PostRequestBook("title", "Author", null, 100);
        });
    }

    @Test
    void priceがnullの場合() {
        assertThrows(BookValidationException.class, () -> {
            new PostRequestBook("title", "Author", "Publisher", null);
        });
    }

}
