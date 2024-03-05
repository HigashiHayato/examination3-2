package com.example.demo.application.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

class ApplicationErrorExceptionHandlerTest {

  @Mock
  Appender<ILoggingEvent> appender;

  @Captor
  ArgumentCaptor<LoggingEvent> captor;

  @InjectMocks
  private ApplicationErrorExceptionHandler exceptionHandler;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    Logger logger = (Logger) LoggerFactory.getLogger(ApplicationErrorExceptionHandler.class);
    logger.addAppender(appender);
  }

  @Test
  void ApplicationErrorExceptionが発生した場合() {
    // setup
    Exception cause = new Exception();
    ApplicationErrorException exception = new ApplicationErrorException("登録", cause);

    // execute
    exceptionHandler.handleApplicationErrorException(exception);

    // assert
    verify(appender).doAppend(captor.capture());
    assertThat(captor.getValue().getLevel()).hasToString("ERROR");
    assertThat(captor.getValue().getMessage()).contains("登録");
  }
}
