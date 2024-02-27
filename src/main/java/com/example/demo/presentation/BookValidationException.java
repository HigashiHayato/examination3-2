package com.example.demo.presentation;

public class BookValidationException extends RuntimeException {
    public BookValidationException(String text) {
        super(text);
    }
}
