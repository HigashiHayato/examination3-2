package com.example.demo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.Book;
import com.example.demo.infrastructure.BookMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {

    @InjectMocks
    private BookService sut;

    @Mock
    private BookMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 全件取得により書籍リストが返される() {
        // setup
        List<Book> bookList = List.of(
                new Book("1", "ワンピース", "oda", "ジャンプ", 300),
                new Book("2", "ワンピース", "higashi", "ジャンプ", 400),
                new Book("3", "ワンピース", "togashi", "ジャンプ", 500),
                new Book("4", "ワンピース", "kishi", "ジャンプ", 600)
        );

        when(mapper.selectAll()).thenReturn(bookList);

        // execute
        List<Book> actual = sut.retrieveAll();

        // assert
        assertEquals(bookList, actual);
    }

    @Test
    void 選択した従業員idが存在する場合() {
        // setup
        Book book = new Book("1", "ワンピース", "oda", "ジャンプ", 300);
        when(mapper.select(any())).thenReturn(book);

         // execute
        Book actual = sut.retrieve("1");

        // assert
        assertEquals(book, actual);
    }

    @Test
    void 選択した書籍idが存在しない場合() {
        // setup
        when(mapper.select(any())).thenReturn(null);

        // execute
        // assert
        assertThatThrownBy(() -> sut.retrieve("99"))
                .isInstanceOf(NotFoundBookException.class)
                .hasMessage("99");
    }


    @Test
    void データを挿入した際挿入したidが返される() {
        // setup
        when(mapper.getMaxId()).thenReturn("88");

        PostRequestBookDto book = new PostRequestBookDto("ワンピース", "oda", "ジャンプ", 300);

        // execute
        String actual = sut.register(book);

        // assert
        assertEquals("89", actual);
    }
}
