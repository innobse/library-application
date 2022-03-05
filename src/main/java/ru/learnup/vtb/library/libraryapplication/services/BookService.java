package ru.learnup.vtb.library.libraryapplication.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import ru.learnup.vtb.library.libraryapplication.mappers.MyMapper;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.repository.JpaBookRepository;
import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;
import ru.learnup.vtb.library.libraryapplication.services.interfaces.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
@Service
public class BookService {

    private JpaBookRepository repository;
    private MyMapper mapper;

    @Autowired
    public BookService(JpaBookRepository repository, MyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Book> getAll() {
        return toDomain(
                repository.findAll());

    }

    public List<Book> getAllByAuthor(Author author) {
        return toDomain(
                repository.findAllByAuthor_Id(author.getId()));
    }

    private List<Book> toDomain(List<BookEntity> entities) {
        return entities.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    private Book toDomain(BookEntity entity) {
        return new Book(entity.getName(), mapper.toDomain(entity.getAuthor()));
    }
}
