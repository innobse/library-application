package ru.learnup.vtb.library.libraryapplication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import ru.learnup.vtb.library.libraryapplication.controllers.dto.AuthorDto;
import ru.learnup.vtb.library.libraryapplication.controllers.dto.ExceptionDto;
import ru.learnup.vtb.library.libraryapplication.mappers.MyMapper;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.services.AuthorService;
import ru.learnup.vtb.library.libraryapplication.services.BookService;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Description
 *
 * @author bse71
 * Created on 01.03.2022
 * @since
 */
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;
    private MyMapper authorMapper;

    @Autowired
    public AuthorController(AuthorService service, BookService bookService, MyMapper authorMapper) {
        this.authorService = service;
        this.bookService = bookService;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public Collection<AuthorDto> getAll() {
        List<Author> authors = authorService.getAll();
        return authors.stream()
                .map(authorMapper::toDto)
                .map(result -> {

                    result.add(linkTo(methodOn(AuthorController.class).get(result.getId())).withSelfRel());
                    return result;
                })
                .collect(toList());
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable("id") long id) {
        Author author = authorService.get(id);
//        List<Book> books = bookService.getAllByAuthor(author);

        final AuthorDto result = authorMapper.toDto(author);



        return result;
    }

    @PostMapping
    public Long create(@RequestBody AuthorDto authorDto) {
        return authorService.add(
                authorMapper.toDomain(authorDto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody AuthorDto authorDto) {
        authorService.update(
                authorMapper.toDomain(authorDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        authorService.deleteById(id);
    }
}
