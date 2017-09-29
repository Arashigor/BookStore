package md.dbalutsel.bookstore.config;

import md.dbalutsel.bookstore.dao.*;
import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.Role;
import md.dbalutsel.bookstore.model.User;
import md.dbalutsel.bookstore.service.BookService;
import md.dbalutsel.bookstore.service.BookServiceImpl;
import md.dbalutsel.bookstore.service.UserService;
import md.dbalutsel.bookstore.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl();
    }

    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public UserService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public Role role() {
        return new Role();
    }

    @Bean
    public RoleDao roleDao() {
        return new RoleDaoImpl();
    }
}