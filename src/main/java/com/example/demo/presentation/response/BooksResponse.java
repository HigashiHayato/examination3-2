package com.example.demo.presentation.response;

import com.example.demo.domain.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 書籍レスポンスのリストを表すレコードクラスです. このクラスは、書籍レスポンスのリストをラップします。
 *
 * @param bookResponseList 書籍レスポンスリスト
 */
public record BooksResponse(
    @JsonProperty("books") List<BookResponse> bookResponseList
) {

  /**
   * 書籍エンティティのリストから BooksResponse インスタンスを生成します.
   *
   * @param bookList 書籍エンティティのリスト
   * @return 書籍レスポンスのリストを表す BooksResponse インスタンス
   */
  public static BooksResponse of(List<Book> bookList) {
    return new BooksResponse(bookList.stream().map(BookResponse::convertToBookResponse).toList());
  }
}
