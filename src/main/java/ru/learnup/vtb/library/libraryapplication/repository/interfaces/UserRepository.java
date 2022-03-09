package ru.learnup.vtb.library.libraryapplication.repository.interfaces;

import ru.learnup.vtb.library.libraryapplication.model.User;

/**
 * Description
 *
 * @author bse71
 * Created on 05.03.2022
 * @since
 */
public interface UserRepository {

    User findByLogin(String login);
}
