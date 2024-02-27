package com.example.demo.application;

import static java.util.Objects.isNull;

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
         */
        public List<Book> retrieveAll() {
            return mapper.selectAll();
        }

        /**
         * 指定された ID の Book を取得します.
         *
         * @param id 取得対象の Book の ID
         * @return 指定された ID の Book
         */
        public Book retrieve(String id) {
                Book book = mapper.select(id);
                if (isNull(book)) {
                        throw new NotFoundBookException(id);
                }
                return book;
        }

}
