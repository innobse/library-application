package ru.learnup.vtb.library.libraryapplication.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.learnup.vtb.library.libraryapplication.controllers.dto.ExceptionDto;

/**
 * Description
 *
 * @author bse71
 * Created on 04.03.2022
 * @since
 */
@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ExceptionDto handler(Exception err) {
        return new ExceptionDto(23, "Упс, что-то пошло не так: " + err.getMessage());
    }
}
