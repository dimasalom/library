package dima.spring.library.repositories;

import static org.assertj.core.api.Assertions.*;

import dima.spring.library.domain.Author;
import dima.spring.library.domain.Book;
import dima.spring.library.domain.Genre;
import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;


@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepositoryJpaImpl bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        val optionalActualStudent = bookRepositoryJpa.findById(1L);
        val expectedStudent = em.find(Book.class, 1L);
        assertThat(optionalActualStudent).isPresent().get().usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @Test
    void save() {
        val author = new Author(0, "Turgeniyev");
        val genre = new Genre(0, "Prose");
        val book = new Book(0, "Poshol v les", author, genre);

        bookRepositoryJpa.save(book);

        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());

        assertThat(actualBook).isNotNull()
                .matches(b -> !b.getName().equals(""))
                .matches(b -> b.getAuthor() != null && b.getAuthor().getId() > 0)
                .matches(b -> b.getGenre() != null && b.getGenre().getId() > 0);
    }

    @Test
    void findAll() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n----------------------------------");

        val books = bookRepositoryJpa.findAll();

        assertThat(books).isNotNull().hasSize(2)
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getAuthor() != null)
                .allMatch(b -> b.getAuthor() != null);

        System.out.println("\n\n\n----------------------------------");

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }

    @Test
    void findByName() {
        val prorok = em.find(Book.class, 1L);
        List<Book> books = bookRepositoryJpa.findByName("Prorok");
        assertThat(books).containsOnly(prorok);
    }

    @Test
    void updateName() {
        val firstBook = em.find(Book.class, 1L);
        String oldName = firstBook.getName();
        em.detach(firstBook);

        bookRepositoryJpa.updateBookNameById(1L, "New Book");
        val updatedBook = em.find(Book.class, 1L);
        assertThat(updatedBook.getName()).isNotEqualTo(oldName).isEqualTo("New Book");
    }

    @Test
    void deleteById() {
        val firstBook = em.find(Book.class, 1L);
        assertThat(firstBook).isNotNull();

        em.detach(firstBook);

        bookRepositoryJpa.deleteById(1L);
        val deletedBook =  em.find(Book.class, 1L);

        assertThat(deletedBook).isNull();

    }
}