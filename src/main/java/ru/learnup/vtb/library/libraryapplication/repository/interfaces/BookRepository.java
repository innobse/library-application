package ru.learnup.vtb.library.libraryapplication.repository.interfaces;

import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;

import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 18.02.2022
 * @since
 */
public interface BookRepository {

    Collection<BookEntity> getAll();
    boolean save(BookEntity book);
    boolean delete(long bookId);
}
