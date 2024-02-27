package com.example.demo.application;

/**
 * POST 処理における書籍を表すレコードクラスです.
 *
 * @param title タイトル
 * @param author 著者
 * @param publisher 出版社
 * @param price 価格
 */
public record PostRequestBookDto(
        String title,
        String author,
        String publisher,
        Integer price
) {

}
