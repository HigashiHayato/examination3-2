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
         * @throws NotFoundBookException 指定された ID の Book が存在しない場合
         */
        public Book retrieve(String id) {
                Book book = mapper.select(id);
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
         */
        public String register(PostRequestBookDto book) {
                String nextId = String.valueOf(Integer.parseInt(mapper.getMaxId()) + 1);
                mapper.insert(new Book(
                        nextId,
                        book.title(),
                        book.author(),
                        book.publisher(),
                        book.price())
                );

                return nextId;
        }

        /**
         * 指定された ID の Book を更新します.
         *
         * @param book 更新する Book の情報
         * @param id 更新対象の Book の ID
         * @throws NotFoundBookException 指定された ID の Book が存在しない場合
         */
        public void update(PostRequestBookDto book, String id) {
                Book existingBook = mapper.select(id);

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
                mapper.update(postBook);
        }
}
