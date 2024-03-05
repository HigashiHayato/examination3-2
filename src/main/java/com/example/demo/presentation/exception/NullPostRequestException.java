package com.example.demo.presentation.exception;

import java.util.List;
import lombok.Getter;

/**
 * ポストリクエストで null のフィールドがあった場合にスローされる例外クラスです.
 */
@Getter
public class NullPostRequestException extends RuntimeException {

  /**
   * null のフィールドを格納するリストです.
   */
  private final List<String> nullList;

  /**
   * 指定されたテキストのリストで新しい NullPostRequestException を構築します.
   *
   * @param nullList null のフィールドのリスト
   */
  public NullPostRequestException(List<String> nullList) {
    this.nullList = nullList;
  }
}
