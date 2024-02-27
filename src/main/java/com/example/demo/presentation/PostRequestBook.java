package com.example.demo.presentation;

import com.example.demo.application.PostRequestBookDto;

public record PostRequestBook(
        String title,
        String author,
        String publisher,
        Integer price
) {
    public PostRequestBookDto convertToDto() {
        return new PostRequestBookDto(title, author, publisher, price);
    }
}
