package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Bootアプリケーションのエントリーポイントとなるクラスです.
 */
@SpringBootApplication
public class DemoApplication {

  /**
   * アプリケーションのメインメソッドです。Spring Bootアプリケーションを起動します.
   *
   * @param args アプリケーション起動時に渡されるコマンドライン引数
   */
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
