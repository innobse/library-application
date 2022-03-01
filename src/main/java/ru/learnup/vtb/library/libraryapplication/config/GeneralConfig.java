package ru.learnup.vtb.library.libraryapplication.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Description
 *
 * @author bse71
 * Created on 16.02.2022
 * @since
 */
@Configuration
public class GeneralConfig {

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("msgs");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
