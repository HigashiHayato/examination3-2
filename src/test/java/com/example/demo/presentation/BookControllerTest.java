package com.example.demo.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.application.BookService;
import com.example.demo.domain.Book;
import com.example.demo.presentation.request.PostRequestBook;
import com.fasterxml.jackson.databind.ObjectMapper;
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

class BookControllerTest {

    private final PostRequestBook POST_REQUEST_BOOK =
            new PostRequestBook("ワンピース", "oda", "ジャンプ", 300);


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
        // setup
        List<Book> bookList = List.of(
                new Book("1", "ワンピース", "oda", "ジャンプ", 300),
                new Book("2", "ワンピース", "higashi", "ジャンプ", 400)
        );

        when(bookService.retrieveAll()).thenReturn(bookList);

        // execute & assert
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

    @Test
    void ゲットリクエストで指定したデータをjsonにして返す() throws Exception {
        // setup
        Book book = new Book("1", "ワンピース", "oda", "ジャンプ", 300);

        when(bookService.retrieve("1")).thenReturn(book);

        // execute & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("title").value("ワンピース"))
                .andExpect(jsonPath("author").value("oda"))
                .andExpect(jsonPath("publisher").value("ジャンプ"))
                .andExpect(jsonPath("price").value(300));

    }

    @Test
    void POSTリクエストにより正常なデータを送った場合() throws Exception {
        //setup
        when(bookService.register(any()))
                .thenReturn("89");

        // execute & assert
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/v1/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(POST_REQUEST_BOOK))
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/v1/books/89"));
    }

    @Test
    void 更新リクエストで指定したidが存在した場合() throws Exception {
        // setup & execute & assert
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(POST_REQUEST_BOOK))
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void 削除リクエストで指定したidが存在した場合() throws Exception {
        // setup & execute & assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/books/1"))
                .andExpect(status().isNoContent());
    }
}
