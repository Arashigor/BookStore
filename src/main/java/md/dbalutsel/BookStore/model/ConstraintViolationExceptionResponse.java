package md.dbalutsel.BookStore.model;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ConstraintViolationExceptionResponse {

    private int errorCode;
    private Set<ConstraintViolation<?>> constraintViolations;

    public ConstraintViolationExceptionResponse(int errorCode, Set<ConstraintViolation<?>> constraintViolations) {
        this.errorCode = errorCode;
        this.constraintViolations = constraintViolations;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(Set<ConstraintViolation<?>> constraintViolations) {
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
