package md.dbalutsel.bookstore.controller.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static md.dbalutsel.bookstore.data.Constants.ACCESS_RESTRICTED_MSG;
import static md.dbalutsel.bookstore.data.Constants.UNAUTHORIZED_MSG;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedExcption(AccessDeniedException ex) {
        return new ResponseEntity<>(ACCESS_RESTRICTED_MSG, FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthException(AuthenticationException ex) {
        return new ResponseEntity<>(UNAUTHORIZED_MSG, UNAUTHORIZED);
    }
}
