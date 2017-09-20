package md.dbalutsel.bookstore.controller.handlers;

import md.dbalutsel.bookstore.model.handlers.ConstraintViolationExceptionResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import static md.dbalutsel.bookstore.data.Constants.BAD_REQUEST_DATA_MSG;
import static md.dbalutsel.bookstore.data.Constants.HIBERNATE_ERROR_MSG;
import static md.dbalutsel.bookstore.data.Constants.NO_DATA_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class DataExceptionHandler {

    @ExceptionHandler({NoResultException.class, ObjectNotFoundException.class})
    public ResponseEntity<String> noResultExceptionHandler() {
        return new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> persistenceExceptionHandler(PersistenceException ex) {
        return new ResponseEntity<>(HIBERNATE_ERROR_MSG, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityExceptionHandler(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(BAD_REQUEST_DATA_MSG, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationExceptionHandler(ConstraintViolationException ex) {

        ConstraintViolationExceptionResponse error =
                new ConstraintViolationExceptionResponse(ex.getConstraintViolations());

        return new ResponseEntity<>(error.toString(), BAD_REQUEST);
    }
}