package dima.spring.library;

import dima.spring.library.domain.Author;
import dima.spring.library.domain.Book;
import dima.spring.library.domain.Genre;
import dima.spring.library.repositories.MyAuthorRepository;
import dima.spring.library.repositories.MyBookRepository;
import dima.spring.library.repositories.MyGenreRepository;
import lombok.val;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;


@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LibraryApplication.class);
        Console.main(args);
    }

    @Autowired
    private MyBookRepository bookRepository;

    @Autowired
    private MyAuthorRepository authorRepository;

    @Autowired
    MyGenreRepository genreRepository;


    @PostConstruct
    public void init() {

        val author = new Author(0, "Turgeniyev");
        val genre = new Genre(0, "Prose");
        val book = new Book(0, "Poshol v les", author, genre);

        bookRepository.save(book);

        System.out.println("----");

        System.out.println("This is method from the book repository: " + bookRepository.findByName("Poshol v les"));

        System.out.println("This is method from the book repository find list: " + bookRepository.findAll());

        System.out.println("This is method from the book repository find by author: " + bookRepository.findBookByAuthor(author));

        System.out.println("This is method from the book repository find by genre: " + bookRepository.findBookByGenre(genre));

        System.out.println("Author repository:");

        System.out.println("Find author by name: " + authorRepository.findAuthorByName("Turgeniyev"));

        String name = "id";

        System.out.println("Find author by name: " + authorRepository.findAll(Sort.by(Sort.Direction.DESC, name)));

        System.out.println("----");

        System.out.println("Find genre " + genreRepository.findAllByNameAndId(genre.getName(), genre.getId()));

        System.out.println("Delete book: ");

//        bookRepository.delete(book);

        System.out.println("Find by author and id" + bookRepository.findByAuthorAndAuthorId(author, author.getId()));

        System.out.println(bookRepository.findByIdGreaterThan(2L));

        Page<Author> bookPage = authorRepository.findAll(PageRequest.of(1, 0));
    }
}
