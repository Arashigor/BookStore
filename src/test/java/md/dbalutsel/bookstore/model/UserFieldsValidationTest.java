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

import static md.dbalutsel.bookstore.data.Constants.ALLOWED_LOGIN;
import static md.dbalutsel.bookstore.data.Constants.ALLOWED_PASSWORD;
import static md.dbalutsel.bookstore.data.Constants.STRING_WITH_40_CHARS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyString;

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
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("All empty and null constraints are violated!", violations, hasSize(2));
    }

    @Test
    public void ShouldFailUserLengthValidationTest() {
        user.setLogin(STRING_WITH_40_CHARS);
        user.setPassword(anyString());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("All length and range constraints are violated!", violations, hasSize(1));
    }

    @Test
    public void ShouldPassAllValidations() {
        user.setLogin(ALLOWED_LOGIN);
        user.setPassword(ALLOWED_PASSWORD);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertThat("Passed all validations!", violations, hasSize(0));
    }
}
