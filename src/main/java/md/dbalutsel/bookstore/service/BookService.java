package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.Book;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Transactional(rollbackFor = Exception.class, readOnly = true)
public interface BookService {

    List<Book> findAll();

    Book findById(Integer id) throws NoSuchObjectException;

    @Transactional(rollbackFor = Exception.class)
    void save(Book book);

    @Transactional(rollbackFor = Exception.class)
    Integer delete(Integer bookId);

    Book findByName(String name);

    List<Book> findByAuthor(String name);

    List<Book> findByGenre(String name);

    List<Book> findByYear(Integer year);
}