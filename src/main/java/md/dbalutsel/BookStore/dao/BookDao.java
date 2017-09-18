package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("BookDao")
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
