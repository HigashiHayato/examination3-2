package com.example.demo.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.demo.application.NotFoundBookResponse;
import com.example.demo.presentation.exception.BookValidationException;
import com.example.demo.presentation.exception.BookValidationExceptionHandler;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class BookValidationExceptionHandlerTest {

    @InjectMocks
    BookValidationExceptionHandler sut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void 正しいレスポンスが返される() {
        // setup
        BookValidationException exception = new BookValidationException("text");

        NotFoundBookResponse expected = new NotFoundBookResponse(
                "0002",
                "request validation error is occurred.",
                new String[]{"text must not be blank"}
        );

        // execute
        ResponseEntity<NotFoundBookResponse> actual = sut.handleValidationException(exception);

        // assert
        assertThat(Objects.requireNonNull(actual.getBody()).code()).isEqualTo(expected.code());
        assertThat(actual.getBody().message()).isEqualTo(expected.message());
        assertThat(actual.getBody().details()).isEqualTo(expected.details());
        assertThat(actual.getStatusCode()).isEqualTo(BAD_REQUEST);
    }


}
