package com.example.demo.infrastructure;

import com.example.demo.domain.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * Book オブジェクトに対するデータベース操作を行う Mapper インターフェースです.
 */
@Mapper
public interface BookMapper {

  /**
   * すべての Book を取得します.
   *
   * @return Book のリスト
   */
  List<Book> selectAll();

  /**
   * 指定された ID の Book を取得します.
   *
   * @param id 取得する Book の ID
   * @return 指定された ID の Book オブジェクト
   */
  Book select(String id);

  /**
   * 新しい Book を挿入します.
   *
   * @param book 挿入する Book オブジェクト
   * @return 挿入が成功した場合は1、それ以外の場合は0
   */
  int insert(Book book);

  /**
   * テーブル内の最大の Book ID を取得します.
   *
   * @return 最大の Book ID
   */
  String getMaxId();

  /**
   * 指定された Book を更新します.
   *
   * @param book 更新する Book オブジェクト
   * @return 更新が成功した場合は1、それ以外の場合は0
   */
  int update(Book book);

  /**
   * 指定された ID の Book を削除します.
   *
   * @param id 削除する Book の ID
   * @return 削除が成功した場合は1、それ以外の場合は0
   */
  int delete(String id);
}
