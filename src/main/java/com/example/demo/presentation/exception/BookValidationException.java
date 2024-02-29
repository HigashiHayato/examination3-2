package com.example.demo.presentation.exception;

public class BookValidationException extends RuntimeException {
    public BookValidationException(String text) {
        super(text);
    }
}
