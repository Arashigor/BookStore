package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);
    List<Book> findAllUserBooks(String username);
}
