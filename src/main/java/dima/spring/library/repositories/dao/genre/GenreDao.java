package dima.spring.library.repositories.dao.genre;

import dima.spring.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    void insert(Genre genre);
    Genre getById(long id);
    void deleteById(long id);
    List<Genre> getAll();
}
