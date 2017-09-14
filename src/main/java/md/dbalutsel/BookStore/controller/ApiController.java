package md.dbalutsel.BookStore.controller;

import md.dbalutsel.BookStore.model.Book;
import md.dbalutsel.BookStore.model.ConstraintViolationExceptionResponse;
import md.dbalutsel.BookStore.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST)
public class ApiController {

    private final BookRepository bookRepository;

    @Autowired
    public ApiController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getbooks")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/addbook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (book != null) {
            bookRepository.save(book);
        }

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> exceptionHandler(ConstraintViolationException ex) {

        ConstraintViolationExceptionResponse error =
                new ConstraintViolationExceptionResponse(HttpStatus.PRECONDITION_FAILED.value(),
                        ex.getConstraintViolations());

        return new ResponseEntity<>(error.toString(), HttpStatus.OK);
    }
}
