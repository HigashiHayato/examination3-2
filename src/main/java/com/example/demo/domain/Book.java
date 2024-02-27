package com.example.demo.domain;

/**
 * 書籍エンティティを表すレコードクラスです.
 *
 * @param id ID
 * @param title タイトル
 * @param author 著者
 * @param publisher 出版社
 * @param price 価格
 */
public record Book(
        String id,
        String title,
        String author,
        String publisher,
        Integer price
) {

}
