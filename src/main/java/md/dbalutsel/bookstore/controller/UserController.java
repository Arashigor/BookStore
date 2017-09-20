package md.dbalutsel.bookstore.controller;

import md.dbalutsel.bookstore.model.User;
import md.dbalutsel.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/new")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.register(user);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<?> verifyUser(@RequestBody User user) {
        return userService.login(user) ? new ResponseEntity<>(OK) : new ResponseEntity<>(NOT_FOUND);
    }
}
