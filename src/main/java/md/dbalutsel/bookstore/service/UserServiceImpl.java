package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.dao.RoleDao;
import md.dbalutsel.bookstore.dao.UserDao;
import md.dbalutsel.bookstore.model.Role;
import md.dbalutsel.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Logger;

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
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
