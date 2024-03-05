package com.example.demo.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.domain.Book;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class BookMapperTest {

  @Autowired
  BookMapper sut;

  @Test
  void 全件取得できる() {
    // setup
    List<Book> expected = List.of(
        new Book("1", "ワンピース", "oda", "ジャンプ", 300),
        new Book("2", "ワンピース", "higashi", "ジャンプ", 400),
        new Book("3", "ワンピース", "togashi", "ジャンプ", 500),
        new Book("4", "ワンピース", "kishi", "ジャンプ", 600)
    );

    // execute
    List<Book> actual = sut.selectAll();

    // assert
    assertEquals(expected, actual);
  }

  @Test
  void idを指定してデータを取得できる() {
    // setup
    Book expected = new Book("1", "ワンピース", "oda", "ジャンプ", 300);

    // execute
    Book actual = sut.select("1");

    // assert
    assertEquals(expected, actual);
  }

  @Test
  void テーブルに1行データが挿入される() {
    // setup
    Book book = new Book("6", "ワンピース", "oda", "ジャンプ", 300);

    // execute
    // assert
    assertEquals(1, sut.insert(book));
  }

  @Test
  void テーブルにあるデータのうち最大のidを取得できる() {
    // setup
    // execute
    String actual = sut.getMaxId();

    // assert
    assertEquals("4", actual);
  }

  @Test
  void テーブルの行を更新できる場合() {
    // setup
    Book book = new Book("1", "ワンピース", "oda", "ジャンプ", 300);

    // execute
    // assert
    assertEquals(1, sut.update(book));
  }

  @Test
  void テーブルの指定した行を削除できる場合() {
    // setup & execute
    int actual = sut.delete("4");

    // assert
    assertEquals(1, actual);
  }

  @Test
  void テーブルの指定した行が存在しておらず削除できない場合() {
    // setup
    // execute
    int actual = sut.delete("0");

    // assert
    assertEquals(0, actual);
  }
}
