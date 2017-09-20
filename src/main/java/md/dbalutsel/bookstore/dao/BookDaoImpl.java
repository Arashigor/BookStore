package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Book;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.rmi.NoSuchObjectException;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private Session session;

    @Override
    public List<Book> findAll() {
        return session.createQuery("from Book", Book.class).getResultList();
    }

    @Override
    public Book findById(Integer id) throws NoSuchObjectException {
        return session.load(Book.class,id);
    }

    @Override
    public Integer save(Book book) {
        return (Integer) session.save(book);
    }

    @Override
    public Integer delete(Integer bookId) {
        return session.createQuery("delete from Book where id=:id")
                .setParameter("id", bookId).executeUpdate();
    }

    @Override
    public Book findByName(String name) {
        return session.createQuery("from  Book where name=:name", Book.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return session.createQuery("from Book where author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return session.createQuery("from Book where genre = :genre", Book.class)
                .setParameter("genre", genre)
                .getResultList();
    }

    @Override
    public List<Book> findByYear(Integer year) {
        return session.createQuery("from Book where year = :year", Book.class)
                .setParameter("year", year)
                .getResultList();
    }
}
