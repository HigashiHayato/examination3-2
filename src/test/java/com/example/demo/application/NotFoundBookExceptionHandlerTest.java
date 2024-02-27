package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class NotFoundBookExceptionHandlerTest {

    @Test
    void 正しいレスポンスが返される() {
        // setup
        NotFoundBookException exception = new NotFoundBookException("99");

        BookResponse expected = new BookResponse(
                "0003",
                "specified book [id = 99] is not found.",
                new String[0]
        );
        // execute
        BookResponse actual = sut.handleNotFoundBookException(exception);

        // assert
        assertThat(actual.code()).isEqualTo(expected.code());
        assertThat(actual.message()).isEqualTo(expected.message());
        assertThat(actual.details()).isEmpty();
        assertThat(actual.getHttpStatus).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
