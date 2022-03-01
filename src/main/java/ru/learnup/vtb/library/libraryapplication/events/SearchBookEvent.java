package ru.learnup.vtb.library.libraryapplication.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Description
 *
 * @author bse71
 * Created on 16.02.2022
 * @since
 */
public class SearchBookEvent extends ApplicationEvent {

    public SearchBookEvent(Info info) {
        super(info);
    }

    @Data
    @AllArgsConstructor
    public static class Info {

        private String bookName;
    }
}
