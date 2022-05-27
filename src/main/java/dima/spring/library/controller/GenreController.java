package dima.spring.library.controller;

import dima.spring.library.domain.Genre;
import dima.spring.library.repositories.MyGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GenreController {

    private final MyGenreRepository genreRepository;

    @Autowired
    public GenreController(MyGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genres")
    public String showListOfGenresFromIndex(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "index";
    }

    @PostMapping("/updategenre/{id}")
    public String updateGenre(@PathVariable(value = "id") long id, @Valid Genre genre, BindingResult result, Model model) {

        if (result.hasErrors()) {
            genre.setId(id);
            return "update-genre";
        }

        genreRepository.save(genre);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid genre Id: " + id));
        model.addAttribute("genre", genre);
        return "update-genre";
    }


    @PostMapping("/addgenre")
    public String addGenre(@Valid Genre genre, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-genre";
        }

        genreRepository.save(genre);
        return "redirect:/index";
    }

    @GetMapping("/deletegenre/{id}")
    public String deleteGenre(@PathVariable("id") long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        genreRepository.delete(genre);
        return "redirect:/index";
    }
}
