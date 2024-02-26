package com.example.demo.steps;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import com.example.demo.Configuration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ja.かつ;
import io.cucumber.java.ja.ならば;
import io.cucumber.java.ja.もし;
import io.cucumber.java.ja.前提;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@CucumberContextConfiguration
@SpringBootTest
public class BookManageStepdefs {

  private static final String POST_BOOK_PATH = "/v1/books";
  private static final String GET_ALL_BOOK_PATH = "/v1/books";
  private static final String GET_BOOK_PATH = "/v1/books/%s";
  private static final String UPDATE_BOOK_PATH = "/v1/books/%s";
  private static final String DELETE_BOOK_PATH = "/v1/books/%s";

  @Autowired
  Configuration configuration;

  @Autowired
  TestContext testContext;

  @前提("適切なBaseURIが指定されている")
  public void 適切なbaseuriが指定されている() {
    String[] schemes = new String[] {"http", "https"};
    UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_LOCAL_URLS);
    boolean actual = urlValidator.isValid(configuration.getBaseUri());
    assertThat(actual).isTrue();
  }

  @もし("ルートURLにアクセスする")
  public void ルートURLにアクセスする() {
    Response response = given().get();
    testContext.setResponse(response);
  }

  @もし("すべての書籍情報を取得する")
  public void すべての書籍情報を取得する() {
    Response response = given().get(GET_ALL_BOOK_PATH);
    testContext.setResponse(response);
  }

  @もし("書籍情報を登録する")
  public void 書籍情報を登録する(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
                          .when()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(Map.of(
                                    "title", employees.get(0).get("title"),
                                    "author", employees.get(0).get("author"),
                                    "publisher", employees.get(0).get("publisher"),
                                    "price", Integer.valueOf(employees.get(0).get("price"))
                            ))
                            .post(POST_BOOK_PATH);
     testContext.setResponse(response);
  }

  @もし("IDを指定して書籍情報を取得する")
  public void IDを指定して書籍情報を取得する() {
    String id = given().get(GET_ALL_BOOK_PATH).jsonPath().getString("books[0].id");
    Response response = given().get(String.format(GET_BOOK_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで書籍情報を取得する")
  public void 存在しないIDで書籍情報を取得する() {
    Response response = given().get(String.format(GET_BOOK_PATH, "2147483647"));
    testContext.setResponse(response);
    testContext.setId("2147483647");
  }

  @もし("空の書籍情報を登録する")
  public void 空の書籍情報を登録する() {
    Response response = given()
                          .when()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(Map.of())
                            .post(POST_BOOK_PATH);
    testContext.setResponse(response);
  }

  @もし("書籍情報を変更する")
  public void 書籍情報を変更する(DataTable dataTable) {
    String id = given().get(GET_ALL_BOOK_PATH).jsonPath().getString("books[0].id");
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
                          .when()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(Map.of(
                                    "title", employees.get(0).get("title"),
                                    "author", employees.get(0).get("author"),
                                    "publisher", employees.get(0).get("publisher"),
                                    "price", Integer.valueOf(employees.get(0).get("price"))
                            ))
                            .patch(String.format(UPDATE_BOOK_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで書籍情報を変更する")
  public void 存在しないIDで書籍情報を変更する(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);

    Response response = given()
                          .when()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(Map.of(
                                    "title", employees.get(0).get("title"),
                                    "author", employees.get(0).get("author"),
                                    "publisher", employees.get(0).get("publisher"),
                                    "price", Integer.valueOf(employees.get(0).get("price"))
                            ))
                            .patch(String.format(UPDATE_BOOK_PATH, "2147483647"));

    testContext.setResponse(response);
    testContext.setId("2147483647");
  }


  @もし("IDを指定して書籍情報を削除する")
  public void IDを指定して書籍情報を削除する() {
    String id = given().get(GET_ALL_BOOK_PATH).jsonPath().getString("books[0].id");
    Response response = given().delete(String.format(DELETE_BOOK_PATH, id));
    testContext.setResponse(response);
    testContext.setId(id);
  }

  @もし("存在しないIDで書籍情報を削除する")
  public void 存在しないIDで書籍情報を削除する() {
    Response response = given().delete(String.format(DELETE_BOOK_PATH, "2147483647"));
    testContext.setResponse(response);
    testContext.setId("2147483647");
  }

  @ならば("HTTPステータスコードとして{int}が返却される")
  public void HTTPステータスコードとしてintが返却される(int statusCode) {
    Response response = testContext.getResponse();
    response.then().statusCode(statusCode);
  }

  @かつ("ContentTypeとして{string}が返却される")
  public void ContentTypeとしてstringが返却される(String contentType) {
    Response response = testContext.getResponse();
    response.then().contentType(contentType);
  }

  @かつ("Locationとして登録した書籍情報にアクセスするURLが返却される")
  public void Locationとして登録した書籍情報にアクセスするURLが返却される() {
    Response response = testContext.getResponse();
    response.then().header("Location", matchesPattern(configuration.getBaseUri() + POST_BOOK_PATH + "/\\d+"));
  }

  @かつ("空のBodyが返却される")
  public void 空のBodyが返却される() {
    Response response = testContext.getResponse();
    response.then().body(emptyString());
  }

  @かつ("[ID、タイトル、著者、出版社、価格]がオブジェクトで返却される")
  public void ID_タイトル_著者_出版社_価格がオブジェクトで返却される() {
    Response response = testContext.getResponse();
    response.then().assertThat().body(matchesJsonSchemaInClasspath("get-book-schema.json"));
  }

  @かつ("[ID、タイトル、著者、出版社、価格]がリストで返却される")
  public void ID_タイトル_著者_出版社_価格がリストで返却される() {
    Response response = testContext.getResponse();
    response.then().assertThat().body(matchesJsonSchemaInClasspath("get-all-books-schema.json"));
  }

  @かつ("書籍情報が変更されている")
  public void 書籍情報が変更されている(DataTable dataTable) {
    List<Map<String, String>> employees = dataTable.asMaps(String.class, String.class);
    String id = testContext.getId();

    given()
      .get(String.format(GET_BOOK_PATH, id))
        .then()
          .statusCode(HttpStatus.OK.value())
          .body("id", equalTo(id))
          .body("title", equalTo(employees.get(0).get("title")))
          .body("author", equalTo(employees.get(0).get("author")))
          .body("publisher", equalTo(employees.get(0).get("publisher")))
          .body("price", equalTo(Integer.valueOf(employees.get(0).get("price"))));
  }

  @かつ("書籍情報が削除されている")
  public void 書籍情報が削除されている() {
    String id = testContext.getId();

    given()
      .delete(String.format(GET_BOOK_PATH, id))
        .then()
          .statusCode(HttpStatus.BAD_REQUEST.value())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body("code", equalTo("0003"))
          .body("message", equalTo(String.format("specified book [id = %s] is not found.", id)))
          .body("details", empty());
  }

  @かつ("[code、message、details]がオブジェクトで返却される")
  public void code_message_detailsがオブジェクトで返却される() {
    Response response = testContext.getResponse();
    response.then().assertThat().body(matchesJsonSchemaInClasspath("error-schema.json"));
  }

  @かつ("codeとして{string}が返却される")
  public void codeとしてstringが返却される(String code) {
    Response response = testContext.getResponse();
    response.then().body("code", equalTo(code));
  }

  @かつ("messageとして{string}が返却される")
  public void messageとしてstringが返却される(String message) {
    Response response = testContext.getResponse();

    if (isNull(testContext.getId())) {
      response.then().body("message", equalTo(message));
    } else {
      response.then().body("message", equalTo(String.format(message, testContext.getId())));
    }
  }

  @かつ("detailsとして空のリストが返却される")
  public void detailsとして空のリストが返却される() {
    Response response = testContext.getResponse();
    response.then().body("details", empty());
  }

  @かつ("detailsとして詳細なエラー内容を含むリストが返却される")
  public void detailsとして詳細なエラー内容を含むリストが返却される() {
    Response response = testContext.getResponse();
    response.then().body("details", not(empty()));
  }

}
