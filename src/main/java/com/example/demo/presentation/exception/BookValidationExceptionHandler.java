package com.example.demo.presentation.exception;

import com.example.demo.application.BookErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookValidationExceptionHandler {

    @ExceptionHandler(BookValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookErrorResponse> handleValidationException(BookValidationException exception) {

        BookErrorResponse response = new BookErrorResponse(
                "0002",
                "request validation error is occurred.",
                createDetailList(exception.getNullList())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private List<String> createDetailList(List<String> nullList) {
        List<String> detailList = new ArrayList<>();
        for (String nullItem : nullList) {
            detailList.add(nullItem + " must not be blank");
        }
        return detailList;
    }
}
