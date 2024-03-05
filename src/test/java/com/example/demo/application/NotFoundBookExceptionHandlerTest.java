package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.demo.application.exception.NotFoundBookException;
import com.example.demo.application.exception.NotFoundBookExceptionHandler;
import com.example.demo.application.response.BookErrorResponse;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class NotFoundBookExceptionHandlerTest {

  @InjectMocks
  NotFoundBookExceptionHandler sut;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 正しいレスポンスが返される() {
    // setup
    NotFoundBookException exception = new NotFoundBookException("99");

    BookErrorResponse expected = new BookErrorResponse(
        "0003",
        "specified book [id = 99] is not found.",
        List.of()
    );
    // execute
    ResponseEntity<BookErrorResponse> actual = sut.handleNotFoundBookException(exception);

    // assert
    assertThat(Objects.requireNonNull(actual.getBody()).code()).isEqualTo(expected.code());
    assertThat(actual.getBody().message()).isEqualTo(expected.message());
    assertThat(actual.getBody().details()).isEmpty();
    assertThat(actual.getStatusCode()).isEqualTo(BAD_REQUEST);
  }

}
