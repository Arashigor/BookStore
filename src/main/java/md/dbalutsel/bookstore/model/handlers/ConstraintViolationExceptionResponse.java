package md.dbalutsel.bookstore.model.handlers;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ConstraintViolationExceptionResponse {

    private Set<ConstraintViolation<?>> constraintViolations;

    public ConstraintViolationExceptionResponse(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ConstraintViolation<?> violation : constraintViolations) {
            s.append(violation.getMessage());
            s.append("\n");
        }
        return s.toString();
    }
}
