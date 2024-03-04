package com.example.demo.presentation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.presentation.exception.BookValidationException;
import com.example.demo.presentation.request.PostRequestBook;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void titleとauthorがnullの場合() {
        assertThrows(BookValidationException.class, () -> {
            new PostRequestBook(null, null, "Publisher", 100);
        });
    }

    @Test
    void titleとauthorがnullの場合2() {
        // setup
        List<String> expected = List.of("title", "author");

        // execute & assert
        assertThatThrownBy(() -> new PostRequestBook(null, null, "Publisher", 100))
                .isInstanceOf(BookValidationException.class)
                .satisfies(e -> assertThat(((BookValidationException) e).getNullList()).isEqualTo(expected));
    }
}
