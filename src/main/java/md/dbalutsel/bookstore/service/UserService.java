package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.User;
import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {HibernateException.class, DataIntegrityViolationException.class},
        propagation = Propagation.REQUIRES_NEW)
public interface UserService {
    Integer register(User user);
    Boolean login(User user);
}
