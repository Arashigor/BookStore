package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.dao.RoleDao;
import md.dbalutsel.bookstore.dao.UserDao;
import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.Role;
import md.dbalutsel.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static md.dbalutsel.bookstore.data.Constants.USER_ROLE;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer save(User user) {
        Role role = roleDao.findByRole(USER_ROLE);
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<Book> findAllUserBooks(String username) {
        return userDao.findAllUserBooks(username);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
