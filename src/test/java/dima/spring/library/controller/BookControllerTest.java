package dima.spring.library.controller;

import dima.spring.library.domain.Author;
import dima.spring.library.domain.Book;
import dima.spring.library.domain.Genre;
import dima.spring.library.repositories.MyAuthorRepository;
import dima.spring.library.repositories.MyBookRepository;
import dima.spring.library.repositories.MyGenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    MyBookRepository bookRepository;

    @Mock
    MyGenreRepository genreRepository;

    @Mock
    MyAuthorRepository authorRepository;

    @InjectMocks
    BookController bookController;

    Book book;

    Genre genre;

    Author author;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        author = Author.builder().id(1L).name("Pushkin").build();
        genre = Genre.builder().id(1L).name("Thriller").build();
        book = Book.builder().id(1L).name("Moroz").author(author).genre(genre).build();

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void showSignUpForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"));

    }

    @Test
    void showUpdateForm() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/editbook/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("update-book"));
    }

    @Test
    void deleteBook() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        mockMvc.perform(get("/deletebook/1"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/index"));
    }
}