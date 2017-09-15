package md.dbalutsel.BookStore.service;

import md.dbalutsel.BookStore.dao.BookDao;
import md.dbalutsel.BookStore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImp implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public List<Book> findByName(String name) {
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
