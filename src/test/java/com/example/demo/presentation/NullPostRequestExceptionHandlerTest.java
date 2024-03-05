package com.example.demo.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.demo.application.BookErrorResponse;
import com.example.demo.presentation.exception.NullPostRequestException;
import com.example.demo.presentation.exception.NullPostRequestExceptionHandler;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class NullPostRequestExceptionHandlerTest {

  @InjectMocks
  NullPostRequestExceptionHandler sut;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 正しいレスポンスが返される() {
    // setup
    List<String> nullList = List.of("title", "author");
    NullPostRequestException exception = new NullPostRequestException(nullList);

    BookErrorResponse expected = new BookErrorResponse(
        "0002",
        "request validation error is occurred.",
        List.of("title must not be blank", "author must not be blank")
    );

    // execute
    ResponseEntity<BookErrorResponse> actual = sut.handleValidationException(exception);

    // assert
    assertThat(Objects.requireNonNull(actual.getBody()).code()).isEqualTo(expected.code());
    assertThat(actual.getBody().message()).isEqualTo(expected.message());
    assertThat(actual.getBody().details()).isEqualTo(expected.details());
    assertThat(actual.getStatusCode()).isEqualTo(BAD_REQUEST);
  }
}
