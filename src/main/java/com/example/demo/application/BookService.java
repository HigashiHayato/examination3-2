package com.example.demo.application;

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

}
