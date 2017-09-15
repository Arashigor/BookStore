package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book",Book.class).getResultList();
        session.getTransaction().commit();
        return booksList;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public List<Book> findByName(String name) {
       return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book where author = :author",Book.class)
                .setParameter("author", author).getResultList();
        session.getTransaction().commit();
        return booksList;
    }

    @Override
    public List<Book> findByGenre(String name) {
        return null;
    }

    @Override
    public List<Book> findByYear(Integer year) {
        return null;
    }
}
