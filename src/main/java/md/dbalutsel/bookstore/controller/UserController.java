package md.dbalutsel.bookstore.controller;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.model.User;
import md.dbalutsel.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static md.dbalutsel.bookstore.data.Constants.NO_DATA_MSG;
import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> registration(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(CREATED);
    }

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @GetMapping("/users/books")
    public ResponseEntity<?> findUserBooks(Principal principal) {
        List<Book> books = userService.findAllUserBooks(principal.getName());
        return (books.isEmpty()) ? new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND) : new ResponseEntity<>(books, OK);
    }
}
