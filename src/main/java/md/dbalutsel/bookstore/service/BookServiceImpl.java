package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.dao.BookDao;
import md.dbalutsel.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Integer id) throws NoSuchObjectException {
        return bookDao.findById(id);
    }

    @Override
    public Integer save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Integer delete(Integer bookId) {
        return bookDao.delete(bookId);
    }

    @Override
    public Book findByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(String name) {
        return bookDao.findByAuthor(name);
    }

    @Override
    public List<Book> findByGenre(String name) {
        return bookDao.findByGenre(name);
    }

    @Override
    public List<Book> findByYear(Integer year) {
        return bookDao.findByYear(year);
    }
}