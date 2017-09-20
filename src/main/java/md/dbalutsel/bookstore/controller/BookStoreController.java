package md.dbalutsel.bookstore.controller;

import md.dbalutsel.bookstore.model.Book;
import md.dbalutsel.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

import static md.dbalutsel.bookstore.data.Constants.NO_DATA_MSG;
import static org.springframework.http.HttpStatus.*;

@RestController
public class BookStoreController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.findAll();
        return (books.isEmpty()) ? new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Integer id) throws NoSuchObjectException {
        Book book = bookService.findById(id);
        return new ResponseEntity<>(book, OK);
    }

    @GetMapping(value = "/books", params = "name")
    public ResponseEntity<Book> getBookByName(@RequestParam("name") String name) {
        Book book = bookService.findByName(name);
        return new ResponseEntity<>(book, OK);
    }

    @GetMapping(value = "/books", params = "author")
    public ResponseEntity<?> getAllBooksByAuthor(@RequestParam("author") String author) {
        List<Book> books = bookService.findByAuthor(author);
        return (books.isEmpty()) ? new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping(value = "/books", params = "year")
    public ResponseEntity<?> getAllBooksByYear(@RequestParam("year") Integer year) {
        List<Book> books = bookService.findByYear(year);
        return (books.isEmpty()) ? new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping(value = "/books", params = "genre")
    public ResponseEntity<?> getAllBooksByGenre(@RequestParam("genre") String genre) {
        List<Book> books = bookService.findByGenre(genre);
        return (books.isEmpty()) ? new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Integer id) {
        return (bookService.delete(id)==1) ? new ResponseEntity<>(NO_CONTENT)
                : new ResponseEntity<>(NO_DATA_MSG, NOT_FOUND);
    }
}