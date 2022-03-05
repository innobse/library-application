package ru.learnup.vtb.library.libraryapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.vtb.library.libraryapplication.mappers.MyMapper;
import ru.learnup.vtb.library.libraryapplication.model.Author;
import ru.learnup.vtb.library.libraryapplication.repository.JpaAuthorRepository;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;

import java.util.ArrayList;
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
public class AuthorService {

    private JpaAuthorRepository repository;
    private MyMapper mapper;

    @Autowired
    public AuthorService(JpaAuthorRepository repository, MyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void printAll() {

        repository.findAll().forEach(System.out::println);
    }

    public Long add(Author author) {
        final AuthorEntity savedAuthor = repository.save(
                new AuthorEntity(null, author.getName(), 0, new ArrayList<>()));
        return savedAuthor.getId();
    }

    @Transactional
    public void update(Author author) {
        final AuthorEntity byId = repository.getById(author.getId());
        repository.save(
                new AuthorEntity(author.getId(), author.getName(), byId.getVersion(), new ArrayList<>()));
    }

    @Cacheable(value = "author.name", key = "#name")
//    @Caching(put = {
//            @CachePut(value = "author.id", key = "#name")
//    },
//            cacheable = {
//            @Cacheable(cacheNames = "author.id", key = "#name")
//    })
    public AuthorEntity getByName(String name) {
        return repository.getByName(name);
    }

    @CachePut(value = "author.name", key = "#authorEntity.name")
    public AuthorEntity saveNew(AuthorEntity authorEntity) {
        return repository.save(authorEntity);
    }

    @CacheEvict(value = "author.name", key = "#author.name")
    public void delete(AuthorEntity author) {
        repository.delete(author);
    }

    public void print(long id) {
        System.out.println(repository.findById(id));
    }


    public List<Author> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Author get(long id) {
        return mapper.toDomain(
                repository.getById(id));
    }

//    private static List<Author> toDomain(List<AuthorEntity> entities) {
//        return entities.stream()
//                .map(AuthorService::toDomain)
//                .collect(Collectors.toList());
//    }
//
//    static Author toDomain(AuthorEntity entity) {
//        return new Author(entity.getId(), entity.getName());
//    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
