package dima.spring.library.repositories.dao.author;

import dima.spring.library.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();
    void insert(Author author);
    Author getById(long id);
    void deleteById(long id);
    List<Author> getAll();
}
