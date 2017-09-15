package md.dbalutsel.BookStore.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = GenreValidator.class)
@Documented
public @interface AllowedGenre {
    String message() default "content.genre.AllowedGenre";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String[] value();
}