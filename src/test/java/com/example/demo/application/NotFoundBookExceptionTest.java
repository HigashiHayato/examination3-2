package com.example.demo.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.application.exception.NotFoundBookException;
import org.junit.jupiter.api.Test;

class NotFoundBookExceptionTest {

  @Test
  void コンストラクタが指定された文字列を正しく含んでいる() {
    // setup
    String id = "99";

    // execute
    NotFoundBookException exception = new NotFoundBookException(id);

    // assert
    assertEquals(id, exception.getMessage());
  }
}
