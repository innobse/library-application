package ru.learnup.vtb.library.libraryapplication.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.repository.JpaBookRepository;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;
import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;
import ru.learnup.vtb.library.libraryapplication.services.interfaces.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
@Service
public class BookService implements ApplicationContextAware {

    private List<Logger> loggers;
    private ApplicationContext ctx;
    private JpaBookRepository repository;

    @Autowired
    public BookService(List<Logger> loggers, JpaBookRepository repository) {
        this.loggers = loggers;
        this.repository = repository;
    }

    public List<Book> getAll() {
        return toDomain(
                repository.findAll());

    }

    public List<Book> getAllByAuthor(Author author) {
        return toDomain(
                repository.findAllByAuthor_Id(author.getId()));
    }

    private static List<Book> toDomain(List<BookEntity> entities) {
        return entities.stream()
                .map(BookService::toDomain)
                .collect(Collectors.toList());
    }

    private static Book toDomain(BookEntity entity) {
        return new Book(entity.getName(), AuthorService.toDomain(entity.getAuthor()));
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
