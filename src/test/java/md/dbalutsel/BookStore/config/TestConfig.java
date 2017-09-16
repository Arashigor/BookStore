package md.dbalutsel.BookStore.config;

import md.dbalutsel.BookStore.dao.BookDao;
import md.dbalutsel.BookStore.dao.BookDaoImpl;
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
    public BookDao bookDao() {
        return new BookDaoImpl();
    }
}
