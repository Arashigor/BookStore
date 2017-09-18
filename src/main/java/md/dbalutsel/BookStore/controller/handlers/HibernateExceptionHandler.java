package md.dbalutsel.BookStore.controller.handlers;

import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import static md.dbalutsel.BookStore.data.Constants.HIBERNATE_ERROR_MSG;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class HibernateExceptionHandler {

    @ExceptionHandler(NestedServletException.class)
    public ResponseEntity<String> hibernateExceptionHandler(HibernateException ex) {
        return new ResponseEntity<>(HIBERNATE_ERROR_MSG, INTERNAL_SERVER_ERROR);
    }
}
