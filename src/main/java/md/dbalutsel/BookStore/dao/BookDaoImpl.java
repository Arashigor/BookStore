package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.model.Book;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book", Book.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return booksList;
    }

    @Override
    public Optional<Book> findById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.get(Book.class,id);
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(book);
    }

    @Override
    public void save(Book book) {
        Session session = sessionFactory.openSession();
        session.save(book);
        session.close();
    }

    @Override
    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<Book> findByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.createQuery("from  Book where name=:name",Book.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book where author = :author",Book.class)
                .setParameter("author", author)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return booksList;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book where genre = :genre", Book.class)
                .setParameter("genre", genre)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return booksList;
    }

    @Override
    public List<Book> findByYear(Integer year) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Book> booksList = session.createQuery("from Book where year = :year", Book.class)
                .setParameter("year", year)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return booksList;
    }
}
