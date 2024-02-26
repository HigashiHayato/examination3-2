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
}
