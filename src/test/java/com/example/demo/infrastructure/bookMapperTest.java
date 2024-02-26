package com.example.demo.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MybatisTest
public class bookMapperTest {
    @Autowired
    BookMapper sut;

    @Test
    void 全件取得できる() {
        // setup
        List<book> expected = List.of(
                new Book("1", "ワンピース", "oda", "ジャンプ", "300"),
                new Book("2", "ワンピース", "higashi", "ジャンプ", "400"),
                new Book("3", "ワンピース", "togashi", "ジャンプ", "500"),
                new Book("4", "ワンピース", "kishi", "ジャンプ", "600")
        );

        // execute
        List<Book> actual = sut.selectAll();

        // assert
        assertEquals(expected, actual);
    }

}
