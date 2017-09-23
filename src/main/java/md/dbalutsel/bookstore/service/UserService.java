package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class,
        propagation = Propagation.REQUIRES_NEW)
public interface UserService extends UserDetailsService {
    Integer save(User user);
    List<Book> findAllUserBooks(String username);
}
