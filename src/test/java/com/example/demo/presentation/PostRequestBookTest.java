package com.example.demo.presentation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.presentation.exception.NullPostRequestException;
import com.example.demo.presentation.request.PostRequestBook;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class PostRequestBookTest {
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    public void setup() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        this.validator = localValidatorFactoryBean;
    }

    @Test
    void titleがnullの場合() {
        assertThrows(NullPostRequestException.class, () -> {
            new PostRequestBook(null, "Author", "Publisher", 100);
        });
    }

    @Test
    void authorがnullの場合() {
        assertThrows(NullPostRequestException.class, () -> {
            new PostRequestBook("title", null, "Publisher", 100);
        });
    }

    @Test
    void publisherがnullの場合() {
        assertThrows(NullPostRequestException.class, () -> {
            new PostRequestBook("title", "Author", null, 100);
        });
    }

    @Test
    void priceがnullの場合() {
        assertThrows(NullPostRequestException.class, () -> {
            new PostRequestBook("title", "Author", "Publisher", null);
        });
    }

    @Test
    void titleとauthorがnullの場合() {
        // setup
        List<String> expected = List.of("title", "author");

        // execute & assert
        assertThatThrownBy(() -> new PostRequestBook(null, null, "Publisher", 100))
                .isInstanceOf(NullPostRequestException.class)
                .satisfies(e -> assertThat(((NullPostRequestException) e).getNullList()).isEqualTo(expected));
    }

    @Test
    void authorが101文字以上の場合() {
        // setup
        PostRequestBook postRequestBook = new PostRequestBook("title", "author".repeat(101), "Publisher", 100);

        // execute
        Set<ConstraintViolation<PostRequestBook>> violations = validator.validate(postRequestBook);

        // assert
        assertEquals(1, violations.size());
        ConstraintViolation<PostRequestBook> violation = violations.iterator().next();
        assertEquals("author は 100 文字以下", violation.getMessage());
    }

    @Test
    void publisherが101文字以上の場合() {
        // setup
        PostRequestBook postRequestBook = new PostRequestBook("title", "author", "Publisher".repeat(101), 100);

        // execute
        Set<ConstraintViolation<PostRequestBook>> violations = validator.validate(postRequestBook);

        // assert
        assertEquals(1, violations.size());
        ConstraintViolation<PostRequestBook> violation = violations.iterator().next();
        assertEquals("publisher は 100 文字以下", violation.getMessage());
    }
}
