package dima.spring.library.repositories;

import dima.spring.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    List<Book> findWithBookNameAndAuthor(String author_name);

    void updateBookNameById(long id, String name);

    void deleteById(long id);
}
