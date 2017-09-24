package md.dbalutsel.bookstore.service;

import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
import md.dbalutsel.bookstore.config.TestSecurityConfig;
import md.dbalutsel.bookstore.model.Book;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Objects;

import static md.dbalutsel.bookstore.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class, TestSecurityConfig.class})
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts= "classpath:/db/insert-book-data.sql")
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    public void findAllTest() {
        List<Book> list = bookService.findAll();
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void findByIdTest() throws NoSuchObjectException {
        Book book = bookService.findById(ALLOWED_ID);
        assertTrue("Should get entity instance", Objects.nonNull(book));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void findByIdFailTest() throws NoSuchObjectException {
        Book book = bookService.findById(WRONG_ID);
    }

    @Test
    public void findByNameTest() {
        Book book = bookService.findByName(ALLOWED_NAME);
        assertTrue("Should get entity instance", Objects.nonNull(book));
    }

    @Test(expected = NoResultException.class)
    public void findByNameFailTest() {
        Book book = bookService.findByName(WRONG_NAME);
    }

    @Test
    public void findByAuthorTest() {
        List<Book> list = bookService.findByAuthor(ALLOWED_AUTHOR);
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void findByYearTest() {
        List<Book> list = bookService.findByYear(ALLOWED_YEAR);
        assertThat("Should contain one entry", list, hasSize(1));
    }


    @Test
    public void findByGenre() {
        List<Book> list = bookService.findByGenre(ALLOWED_GENRE);
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void correctEntityFieldValuesTest() throws NoSuchObjectException {
        Book book = bookService.findById(ALLOWED_ID);

        assertEquals("Should have correct id", book.getId(), ALLOWED_ID);
        assertEquals("Should have correct name", book.getName(), ALLOWED_NAME);
        assertEquals("Should have correct year", book.getYear(), ALLOWED_YEAR);
        assertEquals("Should have correct genre", book.getGenre(), ALLOWED_GENRE);
    }

    @Test
    public void saveTest() {
        Book bookToSave = new Book(ALLOWED_NAME, ALLOWED_AUTHOR, ALLOWED_YEAR, ALLOWED_GENRE);
        Integer bookId = bookService.save(bookToSave);
        assertThat("Should add one entry", bookId, is(1));
    }

    @Test
    public void deleteSuccessTest() {
        Integer rowsAffected = bookService.delete(ALLOWED_ID);
        assertThat("Should affect 1 row", rowsAffected, is(1));
    }

    @Test
    public void deleteFailTest() {
        Integer rowsAffected = bookService.delete(WRONG_ID);
        assertThat("Should not affect any row", rowsAffected, is(0));
    }
}