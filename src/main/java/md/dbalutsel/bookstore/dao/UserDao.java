package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;

import java.util.List;

public interface UserDao {
    Integer save(User user);
    User findByUsername(String username);
    List<Book> findAllUserBooks(String username);
}
