package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void delete(Book book);
    List<Book> findByName(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    List<Book> findByYear(Integer year);
}
