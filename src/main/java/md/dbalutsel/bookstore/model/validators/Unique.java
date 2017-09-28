package md.dbalutsel.bookstore.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = { UniqueValidator.class })
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface Unique {

    String message() default "Value is not unique!";

    Class<?> entityType();

    String property();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ TYPE })
    @Retention(RUNTIME)
    @interface List {
        Unique[] value();
    }
}