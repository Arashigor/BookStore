package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Book;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById(Integer id) throws NoSuchObjectException;
    void save(Book book);
    Integer delete(Integer bookId);
    Book findByName(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    List<Book> findByYear(Integer year);
}
