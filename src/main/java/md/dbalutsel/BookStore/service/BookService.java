package md.dbalutsel.BookStore.service;

import md.dbalutsel.BookStore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    void save(Book book);
    void delete(Book book);
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    List<Book> findByYear(Integer year);
}
