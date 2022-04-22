package dima.spring.library.dao.book;

import dima.spring.library.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    void insert(Book book);

    Book getById(long id);

    void deleteById(long id);

    List<Book> getAll();
}
