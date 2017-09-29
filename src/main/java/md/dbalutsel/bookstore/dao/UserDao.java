package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    User loadByUsername(String username);
    List<Book> findAllUserBooks(String username);
}
