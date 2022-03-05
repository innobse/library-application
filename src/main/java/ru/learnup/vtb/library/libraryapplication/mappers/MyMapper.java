package ru.learnup.vtb.library.libraryapplication.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.learnup.vtb.library.libraryapplication.controllers.dto.AuthorDto;
import ru.learnup.vtb.library.libraryapplication.controllers.dto.BookDto;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;
import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;

import java.util.ArrayList;

/**
 * Description
 *
 * @author bse71
 * Created on 04.03.2022
 * @since
 */
@Mapper(componentModel = "spring")
public interface MyMapper {

    AuthorDto toDto(Author author);
    Author toDomain(AuthorDto authorDto);

    AuthorEntity toEntity(Author author);
    Author toDomain(AuthorEntity authorEntity);

    BookDto toDto(Book book);

    Book toDomain(BookDto bookDto);

    BookEntity toEntity(Book author);
    Book toDomain(BookEntity authorEntity);
}
