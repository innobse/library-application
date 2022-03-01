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

    public void printAll() {

        repository.findAll().forEach(System.out::println);
    }

    public void printAllLike(String pattern) {
        for (BookEntity bookEntity : repository.getMyFilteredResult(pattern)) {
            System.out.println(bookEntity);
        }
    }

    @Transactional
    public void rename(long id, String newName) {
        try {
            final BookEntity forUpdate = repository.getForUpdate(id);
            System.out.println("Объект получен");

            forUpdate.setName(newName);

            Thread.sleep(1000);
            repository.save(forUpdate);
            System.out.println("Объект записан");

        } catch (InterruptedException e) {

        } catch (DataAccessException err) {
            System.out.println("Объект уже был изменен!");
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            readOnly = false,
            rollbackFor = {
                    IOException.class,
                    FileNotFoundException.class,
                    EOFException.class
            },
            noRollbackFor = {RuntimeException.class}
    )
    public void demo(AuthorEntity authorEntity) throws EOFException {



//        final BookEntity targetBook = new BookEntity(null, "999", authorEntity);
//
//        final BookEntity newBook = repository.save(
//                targetBook);
//
//        targetBook.setName("444");
//        targetBook.setId(newBook.getId());
//
////            Thread.sleep(3000);
//
//
//        repository.save(newBook);
//        throw new RuntimeException();

    }

//    public void save(Book book) {
//        repository.save(
//                new BookEntity(null, book.getName(), new AuthorEntity(null, book.getAuthor().getName(), null)));
//    }

    public void delete(long bookId) {
        repository.deleteById(bookId);
    }

    public void error() {
        throw new RuntimeException("УПС!");
    }

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + " успешно создан");
    }

    @PreDestroy
    public void ustroyDestroy() {
        System.out.println(this.getClass().getSimpleName() + " готовится к уничтожению");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    public List<BookEntity> getAll() {
        return repository.findAll();
    }
}
