package com.example.demo.application;

/**
 * 書籍が見つからない場合にスローされる例外クラスです.
 */
public class NotFoundBookException extends RuntimeException {

    /**
     * 指定されたテキストで新しい NotFoundBookException を構築します。
     *
     * @param text handlerで必要となる文字列.
     */
    public NotFoundBookException(String text) {
        super(text);
    }
}
