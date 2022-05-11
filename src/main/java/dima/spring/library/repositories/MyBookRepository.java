package dima.spring.library.repositories;

import dima.spring.library.domain.Author;
import dima.spring.library.domain.Book;
import dima.spring.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyBookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findByName(String name);

    Book findBookByAuthor(Author author);

    Book findBookByGenre(Genre genre);

    List<Book> findByAuthorAndAuthorId(Author author, Long id);

    List<Book> findByIdGreaterThan(Long Id);
}
