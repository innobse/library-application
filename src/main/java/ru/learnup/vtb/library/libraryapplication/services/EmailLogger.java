package ru.learnup.vtb.library.libraryapplication.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.learnup.vtb.library.libraryapplication.services.interfaces.Logger;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
public class EmailLogger implements Logger {

    @Override
    public void print(Object obj) {
        System.out.println("Email: " + obj);
    }
}
