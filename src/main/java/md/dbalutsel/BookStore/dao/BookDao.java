package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void delete(Book book);
    Book findByName(String name);
    Book findByAuthor(String name);
    Book findByGenre(String name);
    Book findByYear(Integer year);
}
