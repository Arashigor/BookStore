package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.User;

public interface UserDao {
    Integer save(User user);
    Boolean verify(User user);
}
