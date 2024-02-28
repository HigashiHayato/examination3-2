package com.example.demo.presentation;

import com.example.demo.application.BookService;
import com.example.demo.presentation.response.BookResponse;
import com.example.demo.presentation.response.BooksResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    /**
     * 指定された ID の Book を取得するエンドポイントです.
     *
     * @param id 取得する Book の ID
     * @return ResponseEntity オブジェクト
     */
    @GetMapping("v1/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse get(@PathVariable String id) {
        System.out.println("=======================");
        System.out.println(id);
        return BookResponse.convertToBookResponse(bookService.retrieve(id));
    }

    /**
     * 新しい Book を登録するエンドポイントです.
     *
     * @param book 登録する PostRequestBook オブジェクト
     * @return ResponseEntity オブジェクト
     */
    @PostMapping("v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> post(@RequestBody @Validated PostRequestBook book, HttpServletRequest request) {
        String nextId = bookService.register(book.convertToDto());

        URI uri = UriComponentsBuilder
                .fromUriString(request.getRequestURL().toString())
                .pathSegment(nextId)
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * 指定された ID の Book を部分的に更新するエンドポイントです.
     *
     * @param book 更新する Book オブジェクト
     * @param id 更新する Book の ID
     */
    @PatchMapping("v1/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(@RequestBody PatchRequestBook book, @PathVariable String id) {
        bookService.update(book.convertToDto(), id);
    }
}
