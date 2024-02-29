package com.example.demo.application.exception;

/**
 * 予期せぬ例外が発生した時にスローされる例外です.
 */
public class ApplicationErrorException extends RuntimeException {

    /**
     * 指定されたメッセージと例外で新しい ApplicationErrorException を構築します.
     *
     * @param message 文字列
     * @param e 例外
     */
    public ApplicationErrorException(String message, Exception e) {
        super(message, e);
    }
}
