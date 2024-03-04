package com.example.demo.presentation.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class BookValidationException extends RuntimeException {
    private final List<String> nullList;
    public BookValidationException(List<String> nullList) {
        this.nullList = nullList;
    }
}
