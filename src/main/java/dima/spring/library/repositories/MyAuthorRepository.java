package dima.spring.library.repositories;

import dima.spring.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyAuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.name = :name")
    Author findAuthorByName(@Param("name") String name);

    List<Author> findAll();
}
