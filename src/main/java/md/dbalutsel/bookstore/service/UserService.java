package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class,
        propagation = Propagation.REQUIRES_NEW)
public interface UserService {
    Integer register(User user);
    User login(String login);
}
