package ru.learnup.vtb.library.libraryapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import ru.learnup.vtb.library.libraryapplication.services.ConsoleLogger;
import ru.learnup.vtb.library.libraryapplication.services.EmailLogger;
import ru.learnup.vtb.library.libraryapplication.services.interfaces.Logger;

/**
 * Description
 *
 * @author bse71
 * Created on 12.02.2022
 * @since
 */
@Configuration
@Profile("logger")
public class LoggersConfig {

    @Bean
    @Scope("prototype")
    public Logger consoleLogger(@Value("${myConfig.logger.console.prefix:default}") String prefix) {
        return new ConsoleLogger(prefix);
    }

//    @Bean
    public Logger emailLogger() {
        return new EmailLogger();
    }
}
