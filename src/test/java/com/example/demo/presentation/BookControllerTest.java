package com.example.demo.presentation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.BookService;
import com.example.demo.domain.Book;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    BookController sut;

    @Mock
    BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    void ゲットリクエストで得た全件データをjsonにして返す() throws Exception {
        //setup
        List<Book> bookList = List.of(
                new Book("1", "ワンピース", "oda", "ジャンプ", 300),
                new Book("2", "ワンピース", "higashi", "ジャンプ", 400)
        );

        when(bookService.retrieveAll()).thenReturn(bookList);

        // execute
        // assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.books[0].id").value("1"))
                .andExpect(jsonPath("$.books[0].title").value("ワンピース"))
                .andExpect(jsonPath("$.books[0].author").value("oda"))
                .andExpect(jsonPath("$.books[0].publisher").value("ジャンプ"))
                .andExpect(jsonPath("$.books[0].price").value("300"))
                .andExpect(jsonPath("$.books[1].id").value("2"))
                .andExpect(jsonPath("$.books[1].title").value("ワンピース"))
                .andExpect(jsonPath("$.books[1].author").value("higashi"))
                .andExpect(jsonPath("$.books[1].publisher").value("ジャンプ"))
                .andExpect(jsonPath("$.books[1].price").value("400"));
    }

}