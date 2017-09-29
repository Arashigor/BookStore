package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
import md.dbalutsel.bookstore.config.TestSecurityConfig;
import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

import static md.dbalutsel.bookstore.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class, TestSecurityConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:/db/insert-book-data.sql",
                                                                       "classpath:/db/insert-user-data.sql"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest() {
        User user = new User(ALLOWED_USERNAME+"1", ALLOWED_PASSWORD, "a"+ALLOWED_EMAIL);
        userService.save(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void ShouldFailToSaveUserBecauseOfUniqueConstraintViolationTest() {
        User user = new User(ALLOWED_USERNAME, ALLOWED_PASSWORD, ALLOWED_EMAIL);
        userService.save(user);
    }

    @Test
    public void loadUserByUsernameTest() {
        User user = (User) userService.loadUserByUsername(ALLOWED_USERNAME);
        assertTrue("Should get entity instance", Objects.nonNull(user));
    }

    @Test(expected = NoResultException.class)
    public void loadUserByUsernameFailTest() {
        userService.loadUserByUsername(WRONG_USERNAME);
    }

    @Test
    public void findAllUserBooksEmptyListTest() {
        List<Book> bookList = userService.findAllUserBooks(ALLOWED_USERNAME);
        assertThat("Should get empty list of user's books", bookList, hasSize(0));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:/db/insert-book-data.sql",
            "classpath:/db/insert-user-data.sql",
            "classpath:/db/insert-userbook-data.sql"})
    public void findAllUserBooksTest() {
        List<Book> bookList = userService.findAllUserBooks(ALLOWED_USERNAME);
        assertThat("Should get list of user's books", bookList, hasSize(1));
    }
}