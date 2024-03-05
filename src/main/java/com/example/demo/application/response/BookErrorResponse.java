package com.example.demo.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 書籍関連の操作に対するレスポンスを表現するレコードクラスです.
 * このクラスはJSON形式でのレスポンスを表現するために Jackson アノテーションを使用しています.
 */
public record BookErrorResponse(
    @JsonProperty("code") String code,
    @JsonProperty("message") String message,
    @JsonProperty("details") List<String> details
) {

}
