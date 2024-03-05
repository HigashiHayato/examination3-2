package com.example.demo.presentation.request;

import static java.util.Objects.isNull;

import com.example.demo.application.Dto.RequestBookDto;
import com.example.demo.presentation.exception.NullPostRequestException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * POST 処理における書籍を表すレコードクラスです.
 *
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     価格
 */
public record PostRequestBook(
    @Length(max = 100, message = "title は 100 文字以下")
    String title,

    @Length(max = 100, message = "author は 100 文字以下")
    String author,

    @Length(max = 100, message = "publisher は 100 文字以下")
    String publisher,

    Integer price
) {

  /**
   * タイトル、著者、出版社、価格において null のものをリストとして NullPostRequestException に渡しスローします.
   *
   * @param title     タイトル
   * @param author    著者
   * @param publisher 出版社
   * @param price     価格
   */
  public PostRequestBook(String title, String author, String publisher, Integer price) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.price = price;

    List<String> nullList = createNullList();
    if (!nullList.isEmpty()) {
      throw new NullPostRequestException(nullList);
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

  /**
   * RequestBookDto へ変換します.
   *
   * @return RequestBookDto
   */
  public RequestBookDto convertToDto() {
    return new RequestBookDto(title, author, publisher, price);
  }
}
