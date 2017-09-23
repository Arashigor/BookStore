package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private Session session;

    @Override
    public Integer save(User user) {
        return (Integer) session.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return session.createQuery("from User where username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
