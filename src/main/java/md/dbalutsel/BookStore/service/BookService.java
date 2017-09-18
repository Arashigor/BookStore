package md.dbalutsel.BookStore.service;

import md.dbalutsel.BookStore.model.Book;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(rollbackFor = HibernateException.class, readOnly = true, propagation = Propagation.REQUIRES_NEW)
public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Integer id);

    @Transactional(rollbackFor = HibernateException.class, propagation = Propagation.REQUIRES_NEW)
    Integer save(Book book);

    @Transactional(rollbackFor = HibernateException.class, propagation = Propagation.REQUIRES_NEW)
    Integer delete(Integer bookId);

    Optional<Book> findByName(String name);

    List<Book> findByAuthor(String name);

    List<Book> findByGenre(String name);

    List<Book> findByYear(Integer year);
}
