package com.example.demo.presentation;

import static java.util.Objects.isNull;

import com.example.demo.application.RequestBookDto;

/**
 * POST 処理における書籍を表すレコードクラスです.
 *
 * @param title タイトル
 * @param author 著者
 * @param publisher 出版社
 * @param price 価格
 */
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

    public RequestBookDto convertToDto() {
        return new RequestBookDto(title, author, publisher, price);
    }
}
