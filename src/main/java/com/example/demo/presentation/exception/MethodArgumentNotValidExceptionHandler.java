package com.example.demo.presentation.exception;

import com.example.demo.application.BookErrorResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        BookErrorResponse response = new BookErrorResponse(
                "",
                "request validation error is occurred.",
                List.of(exception.getMessage() + " は 100 文字以下")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
