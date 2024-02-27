package com.example.demo.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 書籍が見つからない場合の例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class NotFoundBookExceptionHandler {

    /**
     * NotFoundBookException が発生した場合に、適切なエラーレスポンスを生成して返します.
     *
     * @param exception 発生した NotFoundBookException
     * @return エラーレスポンス
     */
    @ExceptionHandler(NotFoundBookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookResponse> handleNotFoundBookException(NotFoundBookException exception) {

        BookResponse response = new BookResponse(
                "0003",
                "specified book [id = " + exception.getMessage() + "] is not found.",
                new String[0]
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
