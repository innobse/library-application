package ru.learnup.vtb.library.libraryapplication.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.learnup.vtb.library.libraryapplication.model.User;
import ru.learnup.vtb.library.libraryapplication.repository.interfaces.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author bse71
 * Created on 05.03.2022
 * @since
 */
@Repository
@Slf4j
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> db = new HashMap<>();

    private PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        db.put("user", new User("user", passwordEncoder.encode("user123"), "ROLE_USER"));
        db.put("admin", new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN"));
        log.info("Шифрованный user123: " + passwordEncoder.encode("user123"));
    }

    @Override
    public User findByLogin(String login) {
        return db.get(login);
    }
}
