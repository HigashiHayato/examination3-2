package com.example.demo.application;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 書籍関連の操作に対するレスポンスを表現するレコードクラスです.
 * このクラスはJSON形式でのレスポンスを表現するために Jackson アノテーションを使用しています.
 */
public record BookResponse(
        @JsonProperty("code") String code,
        @JsonProperty("message") String message,
        @JsonProperty("details") String[] details
) {

}
