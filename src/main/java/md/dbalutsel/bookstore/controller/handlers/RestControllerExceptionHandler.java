package md.dbalutsel.bookstore.controller.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static md.dbalutsel.bookstore.data.Constants.REST_ERROR_MSG;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object msg, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, REST_ERROR_MSG, headers, status, request);
    }
}