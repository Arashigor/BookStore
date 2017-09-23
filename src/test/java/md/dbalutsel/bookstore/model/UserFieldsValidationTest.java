package md.dbalutsel.bookstore.model;

import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
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
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class})
public class UserFieldsValidationTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private User user;

    @Test
    public void ShouldFailUserNullAndEmptyFieldsValidationTest() {
        user.setLogin(null);
        user.setPassword(null);
        user.setEmail(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("All empty and null constraints are violated!", violations, hasSize(4));
    }

    @Test
    public void ShouldFailUserLengthValidationTest() {
        user.setLogin(STRING_WITH_40_CHARS);
        user.setPassword(STRING_WITH_40_CHARS);
        user.setEmail(STRING_WITH_40_CHARS+STRING_WITH_40_CHARS);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("All length and range constraints are violated!", violations, hasSize(3));
    }

    @Test
    public void ShouldPassAllValidations() {
        user.setLogin(ALLOWED_LOGIN);
        user.setPassword(ALLOWED_PASSWORD);
        user.setEmail(ALLOWED_EMAIL);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("Passed all validations!", violations, hasSize(0));
    }
}
