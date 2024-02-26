package com.example.demo.application;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.Book;
import com.example.demo.infrastructure.BookMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BookServiceTest {

    @InjectMocks
    private BookService sut;

    @Mock
    private BookMapper mapper;

    @Test
    void 全件取得により書籍リストが返される() {
        // setup
        List<Book> bookList = List.of(
                new Book("1", "ワンピース", "oda", "ジャンプ", "300"),
                new Book("2", "ワンピース", "higashi", "ジャンプ", "400"),
                new Book("3", "ワンピース", "togashi", "ジャンプ", "500"),
                new Book("4", "ワンピース", "kishi", "ジャンプ", "600")
        );

        when(mapper.selectAll()).thenReturn(bookList);

        // execute
        List<Book> actual = sut.retrieveAll();

        // assert
        assertEquals(bookList, actual);
    }
}
