package com.example.demo.presentation;

import com.example.demo.application.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Book 関連のリクエストを処理するコントローラークラスです.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    /**
     * BookService をインジェクションします.
     */
    private final BookService bookService;

    /**
     * 標準のエンドポイントです。"hello world"という文字列を返します.
     *
     * @return "hello world"
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String standard() {
        return "hello world";
    }

    /**
     * すべての Book を取得するエンドポイントです.
     *
     * @return BooksResponseオブジェクト
     */
    @GetMapping("v1/books")
    @ResponseStatus(HttpStatus.OK)
    public BooksResponse get() {
        return BooksResponse.of(bookService.retrieveAll());
    }
}
