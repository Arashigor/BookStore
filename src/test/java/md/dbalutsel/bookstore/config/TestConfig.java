package md.dbalutsel.bookstore.config;

import md.dbalutsel.bookstore.dao.BookDao;
import md.dbalutsel.bookstore.dao.BookDaoImpl;
import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.service.BookService;
import md.dbalutsel.bookstore.service.BookServiceImp;
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
    public BookService bookService() {
        return new BookServiceImp();
    }

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }
}
