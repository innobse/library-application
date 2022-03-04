package ru.learnup.vtb.library.libraryapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.services.AuthorService;
import ru.learnup.vtb.library.libraryapplication.services.BookService;

import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 01.03.2022
 * @since
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public AuthorController(AuthorService service, BookService bookService) {
        this.authorService = service;
        this.bookService = bookService;
    }

    @GetMapping
    public String showAll(@RequestParam("view") @Nullable String view, Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("objects", authors);
        return view == null ? "listAuthors" : view;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Author author = authorService.get(id);
        List<Book> books = bookService.getAllByAuthor(author);

        model.addAttribute("author", author.getName());
        model.addAttribute("books", books);

        return "author";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute Author author, Model model) {
        authorService.add(author);
        return "redirect:/authors";
    }

    @GetMapping("/new")
    public String addPage() {
        return "newAuthor";
    }
}
