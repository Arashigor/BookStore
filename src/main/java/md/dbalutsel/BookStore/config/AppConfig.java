package md.dbalutsel.BookStore.config;

import md.dbalutsel.BookStore.dao.BookDao;
import md.dbalutsel.BookStore.dao.BookDaoImpl;
import md.dbalutsel.BookStore.service.BookService;
import md.dbalutsel.BookStore.service.BookServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public BookService bookService() {
        return new BookServiceImp();
    }

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }
}
