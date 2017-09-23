package md.dbalutsel.bookstore.controller.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static md.dbalutsel.bookstore.data.Constants.ACCESS_RESTRICTED_MSG;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleExceptionInternal(AccessDeniedException ex) {
        return new ResponseEntity<>(ACCESS_RESTRICTED_MSG, FORBIDDEN);
    }
}
