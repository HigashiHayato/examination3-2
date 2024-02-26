package com.example.demo.domain;

/**
 * 書籍エンティティを表します.
 *
 * @param id ID
 * @param title タイトル
 * @param author 著者
 * @param publisher 出版社
 * @param price 価格
 */
public record Book(
        /**
         * 書籍の ID.
         */
        String id,

        /**
         * 書籍のタイトル.
         */
        String title,

        /**
         * 書籍の著者.
         */
        String author,

        /**
         * 書籍の出版社.
         */
        String publisher,

        /**
         * 書籍の価格.
         */
        String price
) {

}
