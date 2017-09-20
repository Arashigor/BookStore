package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.Book;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Transactional(rollbackFor = Exception.class,
        readOnly = true,
        propagation = Propagation.REQUIRES_NEW)
public interface BookService {

    List<Book> findAll();

    Book findById(Integer id) throws NoSuchObjectException;

    @Transactional(rollbackFor = HibernateException.class, propagation = Propagation.REQUIRES_NEW)
    Integer save(Book book);

    @Transactional(rollbackFor = HibernateException.class, propagation = Propagation.REQUIRES_NEW)
    Integer delete(Integer bookId);

    Book findByName(String name);

    List<Book> findByAuthor(String name);

    List<Book> findByGenre(String name);

    List<Book> findByYear(Integer year);
}