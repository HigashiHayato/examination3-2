package com.example.demo.presentation.exception;

import com.example.demo.application.BookErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ポストリクエストで null のフィールドがあった場合の例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class NullPostRequestExceptionHandler {

    /**
     * NullPostRequestException が発生した場合に、適切なエラーレスポンスを生成して返します.
     *
     * @param exception 発生した NullPostRequestException
     * @return エラーレスポンス
     */
    @ExceptionHandler(NullPostRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookErrorResponse> handleValidationException(
            NullPostRequestException exception
    ) {

        BookErrorResponse response = new BookErrorResponse(
                "0002",
                "request validation error is occurred.",
                createDetailList(exception.getNullList())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private List<String> createDetailList(List<String> nullList) {
        List<String> detailList = new ArrayList<>();
        for (String nullItem : nullList) {
            detailList.add(nullItem + " must not be blank");
        }
        return detailList;
    }
}
