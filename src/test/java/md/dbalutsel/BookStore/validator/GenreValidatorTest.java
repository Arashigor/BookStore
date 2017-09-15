package md.dbalutsel.BookStore.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintValidatorContext;

import static md.dbalutsel.BookStore.data.Data.ALLOWED_GENRE;
import static md.dbalutsel.BookStore.data.Data.NOT_ALLOWED_GENRE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GenreValidatorTest {

    @InjectMocks
    private GenreValidator genreValidator;

    @Mock
    private AllowedGenre allowedGenre;

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder builder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(allowedGenre.value()).thenReturn(new String[]{ALLOWED_GENRE});
    }

    @Test
    public void testValidatorWithWrongValues() {
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);

        genreValidator.initialize(allowedGenre);

        assertFalse(genreValidator.isValid(NOT_ALLOWED_GENRE, context));

        verify(allowedGenre).value();
        verify(context).disableDefaultConstraintViolation();
        verify(context).buildConstraintViolationWithTemplate(anyString());
        verify(builder).addConstraintViolation();
        verifyNoMoreInteractions(allowedGenre,context);
    }

    @Test
    public void testValidatorWithCorrectValues() {
        genreValidator.initialize(allowedGenre);

        assertTrue(genreValidator.isValid(ALLOWED_GENRE, context));

        verify(allowedGenre).value();
        verifyNoMoreInteractions(allowedGenre,context);
    }
}