package com.example.demo.application;

import static java.util.Objects.isNull;

import com.example.demo.application.dto.RequestBookDto;
import com.example.demo.application.exception.ApplicationErrorException;
import com.example.demo.application.exception.NotFoundBookException;
import com.example.demo.domain.Book;
import com.example.demo.infrastructure.BookMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Book に関するビジネスロジックを提供するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class BookService {

  /**
   * BookMapperをインジェクションします.
   */
  private final BookMapper mapper;

  /**
   * 全ての Book を取得します.
   *
   * @return 全ての Book のリスト
   * @throws ApplicationErrorException 予期せぬ例外発生時
   */
  public List<Book> retrieveAll() {
    try {
      return mapper.selectAll();
    } catch (Exception e) {
      throw new ApplicationErrorException("全件取得", e);
    }
  }

  /**
   * 指定された ID の Book を取得します.
   *
   * @param id 取得対象の Book の ID
   * @return 指定された ID の Book
   * @throws NotFoundBookException     指定された ID の Book が存在しない場合
   * @throws ApplicationErrorException 予期せぬ例外発生時
   */
  public Book retrieve(String id) {
    Book book;
    try {
      book = mapper.select(id);
    } catch (Exception e) {
      throw new ApplicationErrorException("指定取得", e);
    }
    if (isNull(book)) {
      throw new NotFoundBookException(id);
    }
    return book;
  }

  /**
   * 新しい Book を登録します.
   *
   * @param book 登録する PostRequestBookDto
   * @return 挿入した書籍の ID
   * @throws ApplicationErrorException 予期せぬ例外発生時
   */
  public String register(RequestBookDto book) {
    try {
      String nextId = String.valueOf(Integer.parseInt(mapper.getMaxId()) + 1);
      mapper.insert(new Book(
          nextId,
          book.title(),
          book.author(),
          book.publisher(),
          book.price())
      );

      return nextId;
    } catch (Exception e) {
      throw new ApplicationErrorException("登録", e);
    }
  }

  /**
   * 指定された ID の Book を更新します.
   *
   * @param book 更新する Book の情報
   * @param id   更新対象の Book の ID
   * @throws NotFoundBookException     指定された ID の Book が存在しない場合
   * @throws ApplicationErrorException 予期せぬ例外発生時
   */
  public void update(RequestBookDto book, String id) {
    Book existingBook;
    try {
      existingBook = mapper.select(id);
    } catch (Exception e) {
      throw new ApplicationErrorException("指定取得", e);
    }

    if (isNull(existingBook)) {
      throw new NotFoundBookException(id);
    }

    Book postBook = new Book(
        id,
        isNull(book.title()) ? existingBook.title() : book.title(),
        isNull(book.author()) ? existingBook.author() : book.author(),
        isNull(book.publisher()) ? existingBook.publisher() : book.publisher(),
        isNull(book.price()) ? existingBook.price() : book.price()
    );
    try {
      mapper.update(postBook);
    } catch (Exception e) {
      throw new ApplicationErrorException("更新", e);
    }
  }

  /**
   * 指定された ID の Book を削除します.
   *
   * @param id 削除対象の Book の ID
   * @throws ApplicationErrorException 予期せぬ例外発生時
   */
  public void delete(String id) {
    int deleteStatusCode;
    try {
      deleteStatusCode = mapper.delete(id);
    } catch (Exception e) {
      throw new ApplicationErrorException("削除", e);
    }
    if (deleteStatusCode == 0) {
      throw new NotFoundBookException(id);
    }
  }
}
