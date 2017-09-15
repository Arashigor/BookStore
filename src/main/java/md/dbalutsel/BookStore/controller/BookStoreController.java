package md.dbalutsel.BookStore.controller;

import md.dbalutsel.BookStore.dao.BookDaoImpl;
import md.dbalutsel.BookStore.model.Book;
import md.dbalutsel.BookStore.model.ConstraintViolationExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;

@RestController
public class BookStoreController {

    @Autowired
    private BookDaoImpl bookDao;

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return new ResponseEntity<>(bookDao.findAll(),HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Long id) {
        Book book = bookDao.findById(id);
        if (book!=null) {
            return new ResponseEntity<>(book,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        bookDao.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> exceptionHandler(ConstraintViolationException ex) {

        ConstraintViolationExceptionResponse error =
                new ConstraintViolationExceptionResponse(ex.getConstraintViolations());

        return new ResponseEntity<>(error.toString(), HttpStatus.OK);
    }
}
