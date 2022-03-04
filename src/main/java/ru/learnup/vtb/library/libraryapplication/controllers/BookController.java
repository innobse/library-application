package ru.learnup.vtb.library.libraryapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.learnup.vtb.library.libraryapplication.model.Book;
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
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        final List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "table";
    }
}
