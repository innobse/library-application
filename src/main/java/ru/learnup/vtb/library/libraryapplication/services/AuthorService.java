package ru.learnup.vtb.library.libraryapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.learnup.vtb.library.libraryapplication.repository.JpaAuthorRepository;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;

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

    @Autowired
    public AuthorService(JpaAuthorRepository repository) {
        this.repository = repository;
    }

    public void printAll() {

        repository.findAll().forEach(System.out::println);
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
}
