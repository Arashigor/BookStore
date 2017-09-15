package md.dbalutsel.BookStore.config;

import md.dbalutsel.BookStore.controller.BookStoreController;
import md.dbalutsel.BookStore.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class TestConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    public BookStoreController bookStoreController() {
        return new BookStoreController();
    }
}
