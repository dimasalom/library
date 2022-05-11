package dima.spring.library.repositories;

import dima.spring.library.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findByName(String name);

    Book findByAuthor();
}
