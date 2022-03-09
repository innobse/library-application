package ru.learnup.vtb.library.libraryapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.learnup.vtb.library.libraryapplication.model.User;
import ru.learnup.vtb.library.libraryapplication.repository.interfaces.UserRepository;

/**
 * Description
 *
 * @author bse71
 * Created on 05.03.2022
 * @since
 */
@Component
public class MyUserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public MyUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userInDb = repository.findByLogin(username);
        if (userInDb == null) throw new UsernameNotFoundException("Пользователь тю-тю...");
        return userInDb;
    }
}
