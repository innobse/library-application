package ru.learnup.vtb.library.libraryapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import ru.learnup.vtb.library.libraryapplication.model.Book;
import ru.learnup.vtb.library.libraryapplication.repository.entities.AuthorEntity;
import ru.learnup.vtb.library.libraryapplication.repository.entities.BookEntity;
import ru.learnup.vtb.library.libraryapplication.services.AuthorService;
import ru.learnup.vtb.library.libraryapplication.services.BookService;

import java.io.EOFException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EntityScan({"ru.learnup.vtb.library.libraryapplication.repository.entities"})
@EnableCaching
public class LibraryApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(LibraryApplication.class, args);
        System.out.println(ctx.getBean(ViewResolver.class));


    }

}
