package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.dao.BookDao;
import md.dbalutsel.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
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
    public Optional<Book> findByName(String name) {
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