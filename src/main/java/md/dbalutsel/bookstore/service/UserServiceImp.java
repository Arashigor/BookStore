package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.dao.UserDao;
import md.dbalutsel.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Integer register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public Boolean login(User user) {
        return null;
    }
}
