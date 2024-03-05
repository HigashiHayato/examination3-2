package com.example.demo.presentation.request;

import com.example.demo.application.RequestBookDto;

/**
 * PATCH 処理における書籍を表すレコードクラスです.
 *
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     価格
 */
public record PatchRequestBook(
    String title,
    String author,
    String publisher,
    Integer price

) {

  public RequestBookDto convertToDto() {
    return new RequestBookDto(title, author, publisher, price);
  }
}
