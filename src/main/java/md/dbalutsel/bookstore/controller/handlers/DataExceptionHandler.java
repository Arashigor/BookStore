package md.dbalutsel.bookstore.controller.handlers;

import md.dbalutsel.bookstore.model.handlers.ConstraintViolationExceptionResponse;
import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import static md.dbalutsel.bookstore.data.Constants.BAD_REQUEST_DATA_MSG;
import static md.dbalutsel.bookstore.data.Constants.HIBERNATE_ERROR_MSG;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class DataExceptionHandler {

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