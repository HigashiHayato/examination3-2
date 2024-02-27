package com.example.demo.presentation;

import com.example.demo.domain.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * レスポンスとして返される書籍情報を表すレコードクラスです.
 */
public record BookResponse(
        @JsonProperty("id") String id,
        @JsonProperty("title") String title,
        @JsonProperty("author") String author,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("price") Integer price
) {
    /**
     * 指定された Book オブジェクトから BookResponse を作成して返します.
     *
     * @param book 書籍情報を含む Book オブジェクト
     * @return BookResponse オブジェクト
     */
    public static BookResponse convertToBookResponse(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.author(),
                book.publisher(),
                book.price()
        );
    }
}
