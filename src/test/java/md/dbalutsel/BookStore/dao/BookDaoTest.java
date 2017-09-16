package md.dbalutsel.BookStore.dao;

import md.dbalutsel.BookStore.config.TestConfig;
import md.dbalutsel.BookStore.config.TestDataConfig;
import md.dbalutsel.BookStore.model.Book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static md.dbalutsel.BookStore.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class})
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/db/insert-data.sql")
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    public void findAllTest() {
        List<Book> list = bookDao.findAll();

        assertNotNull(list);
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void findByIdTest() {
        Optional<Book> bookOptional = bookDao.findById(ALLOWED_ID);
        assertTrue("Should get entity instance", bookOptional.isPresent());
    }

    @Test
    public void findByName() {
        Optional<Book> bookOptional = bookDao.findByName(ALLOWED_NAME);
        assertTrue("Should get entity instance", bookOptional.isPresent());
    }

    @Test
    public void findByAuthorTest() {
        List<Book> list = bookDao.findByAuthor(ALLOWED_AUTHOR);

        assertNotNull(list);
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void findByYearTest() {
        List<Book> list = bookDao.findByYear(ALLOWED_YEAR);

        assertNotNull(list);
        assertThat("Should contain one entry", list, hasSize(1));
    }


    @Test
    public void findByGenre() {
        List<Book> list = bookDao.findByGenre(ALLOWED_GENRE);

        assertNotNull(list);
        assertThat("Should contain one entry", list, hasSize(1));
    }

    @Test
    public void correctEntityFieldValuesTest() {
        Book book = bookDao.findById(ALLOWED_ID).orElse(new Book());

        assertEquals("Should have correct id", book.getId(), ALLOWED_ID);
        assertEquals("Should have correct name", book.getName(), ALLOWED_NAME);
        assertEquals("Should have correct year", book.getYear(), ALLOWED_YEAR);
        assertEquals("Should have correct genre", book.getGenre(), ALLOWED_GENRE);
    }

    @Test
    public void saveTest() {
        Book bookToSave = new Book(ALLOWED_ID+1, ALLOWED_NAME, ALLOWED_AUTHOR, ALLOWED_YEAR, ALLOWED_GENRE);
        bookDao.save(bookToSave);
        Optional<Book> fetchedBook = bookDao.findById(ALLOWED_ID+1);

        assertTrue("Should get recently saved entry", fetchedBook.isPresent());
    }

    @Test
    public void deleteTest() {
        Book book = new Book(ALLOWED_ID, ALLOWED_NAME, ALLOWED_AUTHOR, ALLOWED_YEAR, ALLOWED_GENRE);
        bookDao.delete(book);

        Optional<Book> fetchedBook = bookDao.findById(ALLOWED_ID);
        assertFalse("Should not get recently deleted entry", fetchedBook.isPresent());
    }
}
