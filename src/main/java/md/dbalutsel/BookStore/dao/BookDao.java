package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("BookDao")
public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    void save(Book book);
    void delete(Book book);
    Optional<Book> findByName(String name);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    List<Book> findByYear(Integer year);
}
