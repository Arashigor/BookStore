package md.dbalutsel.BookStore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class GenreValidator implements ConstraintValidator<AllowedGenre, String> {

   private static String[] allowedGenres;

   public void initialize(AllowedGenre constraint) {
      allowedGenres = constraint.value();
   }


   public boolean isValid(String genre, ConstraintValidatorContext context) {
      if (genre == null) {
         return true;
      }

      boolean isValid = Stream.of(allowedGenres).anyMatch(s -> s.equals(genre.toUpperCase()));

      if (!isValid) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(
                 "{category.genre.AllowedGenre}")
                 .addConstraintViolation();
      }

      return isValid;
   }

}
