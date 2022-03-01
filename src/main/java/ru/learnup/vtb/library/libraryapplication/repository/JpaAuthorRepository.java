package ru.learnup.vtb.library.libraryapplication.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;
import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedSubgraph;
import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 22.02.2022
 * @since
 */
public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @EntityGraph(value = "author-with-books")
    @Override
    List<AuthorEntity> findAll();

    AuthorEntity getByName(String name);
}
