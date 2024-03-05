package com.example.demo.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 予期せぬ例外のハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class ApplicationErrorExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(ApplicationErrorExceptionHandler.class);

  /**
   * ApplicationErrorException が発生した場合に、原因となる処理と例外の情報をログに出力します.
   *
   * @param e ApplicationErrorException
   */
  @ExceptionHandler(ApplicationErrorException.class)
  public void handleApplicationErrorException(ApplicationErrorException e) {
    logger.error(e.getMessage(), e);
  }
}
