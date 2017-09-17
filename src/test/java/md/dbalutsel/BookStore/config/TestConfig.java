package md.dbalutsel.BookStore.config;

import md.dbalutsel.BookStore.dao.BookDao;
import md.dbalutsel.BookStore.dao.BookDaoImpl;
import md.dbalutsel.BookStore.model.Book;
import md.dbalutsel.BookStore.service.BookService;
import md.dbalutsel.BookStore.service.BookServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableTransactionManagement
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
