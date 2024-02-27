package com.example.demo.presentation;

import static java.util.Objects.isNull;

import com.example.demo.application.PostRequestBookDto;

public record PostRequestBook(
        String title,
        String author,
        String publisher,
        Integer price
) {
    public PostRequestBook {
        if (isNull(title)) {
            throw new BookValidationException("title");
        }
        if (isNull(author)) {
            throw new BookValidationException("author");
        }
        if (isNull(publisher)) {
            throw new BookValidationException("publisher");
        }
        if (isNull(price)) {
            throw new BookValidationException("price");
        }
    }

    public PostRequestBookDto convertToDto() {
        return new PostRequestBookDto(title, author, publisher, price);
    }
}
