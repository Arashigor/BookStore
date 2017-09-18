package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    Integer save(Book book);
    Integer delete(Integer bookId);
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    List<Book> findByYear(Integer year);
}
