package dima.spring.library.repositories;

import dima.spring.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MyGenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAllByNameAndId(String name, Long Id);

    Optional<Genre> findById(Long id);

    List<Genre> findAll();
}
