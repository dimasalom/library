package dima.spring.library.repositories;

import dima.spring.library.domain.Author;
import dima.spring.library.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("genre-entity-graph");

        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Book> findWithBookNameAndAuthor(String author_name) {
        TypedQuery<Book> query = em.createQuery("select b.author from Book b join b.author a where a.name = :author_name", Book.class);
        query.setParameter("author_name", author_name);
        return query.getResultList();
    }

    @Override
    public void updateBookNameById(long id, String name) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
