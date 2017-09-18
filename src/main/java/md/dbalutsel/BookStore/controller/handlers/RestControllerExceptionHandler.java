package md.dbalutsel.BookStore.controller.handlers;

import md.dbalutsel.BookStore.model.ConstraintViolationExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static md.dbalutsel.BookStore.data.Constants.REST_ERROR_MSG;
import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object msg, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, REST_ERROR_MSG, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationExceptionHandler(ConstraintViolationException ex) {

        ConstraintViolationExceptionResponse error =
                new ConstraintViolationExceptionResponse(ex.getConstraintViolations());

        return new ResponseEntity<>(error.toString(), OK);
    }
}
