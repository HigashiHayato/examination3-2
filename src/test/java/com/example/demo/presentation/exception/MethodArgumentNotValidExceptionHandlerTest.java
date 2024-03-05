package com.example.demo.presentation.exception;

import com.example.demo.application.response.BookErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MethodArgumentNotValidExceptionHandlerTest {

  @Test
  void MethodArgumentNotValidExceptionが発生した場合() {
    // Setup
    MethodArgumentNotValidExceptionHandler handler = new MethodArgumentNotValidExceptionHandler();
    MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

    BookErrorResponse expectedResponse = new BookErrorResponse(
        "",
        "request validation error is occurred.",
        List.of("null は 100 文字以下")
    );

    // Execute
    ResponseEntity<BookErrorResponse> responseEntity = handler.handleMethodArgumentNotValidException(exception);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(expectedResponse, responseEntity.getBody());
  }
}
