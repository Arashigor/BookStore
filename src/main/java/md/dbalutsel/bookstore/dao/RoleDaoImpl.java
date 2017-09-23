package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private Session session;

    @Override
    public Role findByRole(String role) {
        return session.createQuery("from Role where name=:name", Role.class)
                .setParameter("name", role)
                .getSingleResult();
    }
}
