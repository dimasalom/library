package dima.spring.library.controller;

import dima.spring.library.domain.Book;
import dima.spring.library.repositories.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final MyBookRepository bookRepository;

    @Autowired
    public BookController(MyBookRepository myBookRepository) {
        this.bookRepository = myBookRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @GetMapping("/")
    public String showListOfBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/index")
    public String showListOfBooksFromIndex(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-book";
        }

        bookRepository.save(book);
        return "redirect:/index";
    }

    @PostMapping("/updatebook/{id}")
    public String updateBook(@PathVariable(value = "id") long id, @Valid Book book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }
        bookRepository.save(book);
        return "redirect:/index";
    }

    @GetMapping("/editbook/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        return "update-book";
    }

    @GetMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book id: " + id));
        bookRepository.delete(book);
        return "redirect:/index";
    }
}
