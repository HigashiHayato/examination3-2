package com.example.demo.application.Dto;

/**
 * リクエスト処理における書籍を表すレコードクラスです.
 *
 * @param title     タイトル
 * @param author    著者
 * @param publisher 出版社
 * @param price     価格
 */
public record RequestBookDto(
    String title,
    String author,
    String publisher,
    Integer price
) {
}
