package md.dbalutsel.bookstore.model;

import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
import md.dbalutsel.bookstore.config.TestSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.ConstraintViolation;

import java.util.Set;

import static md.dbalutsel.bookstore.data.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class, TestSecurityConfig.class})
public class BookFieldsValidationTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private Book book;

    @Test
    public void ShouldFailBookNullAndEmptyFieldsValidationTest() {
        book.setName(null);
        book.setYear(null);
        book.setAuthor(null);
        book.setGenre(null);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat("All empty and null constraints are violated!", violations, hasSize(7));
    }

    @Test
    public void ShouldFailBookRangeAndLengthValidationTest() {
        book.setName(STRING_WITH_40_CHARS);
        book.setAuthor(STRING_WITH_40_CHARS);
        book.setGenre(STRING_WITH_40_CHARS);
        book.setYear(WRONG_YEAR);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat("All length and range constraints are violated!", violations, hasSize(4));
    }

    @Test
    public void ShouldPassAllValidations() {
        book.setName(ALLOWED_NAME);
        book.setYear(ALLOWED_YEAR);
        book.setAuthor(ALLOWED_AUTHOR);
        book.setGenre(ALLOWED_GENRE);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat("Passed all validations!", violations, hasSize(0));
    }
}