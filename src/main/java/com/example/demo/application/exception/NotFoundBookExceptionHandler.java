package com.example.demo.application.exception;

import com.example.demo.application.NotFoundBookResponse;
import java.util.List;
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
    public ResponseEntity<NotFoundBookResponse> handleNotFoundBookException(NotFoundBookException exception) {

        NotFoundBookResponse response = new NotFoundBookResponse(
                "0003",
                "specified book [id = " + exception.getMessage() + "] is not found.",
                List.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
