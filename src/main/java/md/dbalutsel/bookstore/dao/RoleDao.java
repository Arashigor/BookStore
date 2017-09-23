package md.dbalutsel.bookstore.dao;

import md.dbalutsel.bookstore.model.Role;

public interface RoleDao {
    Role findByRole(String role);
}
