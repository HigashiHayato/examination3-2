package com.example.demo.presentation.request;

import static java.util.Objects.isNull;

import com.example.demo.application.RequestBookDto;
import com.example.demo.presentation.exception.BookValidationException;
import java.util.ArrayList;
import java.util.List;

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
    public PostRequestBook(String title, String author, String publisher, Integer price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;

        List<String> nullList = createNullList();
        if (nullList.size() >= 1) {
            throw new BookValidationException(nullList);
        }
    }


    private List<String> createNullList() {
        List<String> nullList = new ArrayList<>();

        if (isNull(title)) {
            nullList.add("title");
        }
        if (isNull(author)) {
            nullList.add("author");
        }
        if (isNull(publisher)) {
            nullList.add("publisher");
        }
        if (isNull(price)) {
            nullList.add("price");
        }
        return nullList;
    }

    public RequestBookDto convertToDto() {
        return new RequestBookDto(title, author, publisher, price);
    }
}
