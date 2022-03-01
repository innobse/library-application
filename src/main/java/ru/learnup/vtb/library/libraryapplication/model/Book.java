package ru.learnup.vtb.library.libraryapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
@AllArgsConstructor
@Data
public class Book {

    private String name;
    private Author author;

    public void setBookmark(int page) {

    }

}
