package md.dbalutsel.BookStore.controller;

import md.dbalutsel.BookStore.model.Book;
import md.dbalutsel.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
public class BookStoreController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return (books.isEmpty()) ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Integer id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(book1 -> new ResponseEntity<>(book1, OK)).orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping(value = "/books", params = "name")
    public ResponseEntity<Book> getBookByName(@RequestParam("name") String name) {
        Optional<Book> book = bookService.findByName(name);
        return book.map(book1 -> new ResponseEntity<>(book1, OK)).orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping(value = "/books", params = "author")
    public ResponseEntity<Iterable<Book>> getAllBooksByAuthor(@RequestParam("author") String author) {
        List<Book> books = bookService.findByAuthor(author);
        return (books.isEmpty()) ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping(value = "/books", params = "year")
    public ResponseEntity<Iterable<Book>> getAllBooksByYear(@RequestParam("year") Integer year) {
        List<Book> books = bookService.findByYear(year);
        return (books.isEmpty()) ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @GetMapping(value = "/books", params = "genre")
    public ResponseEntity<Iterable<Book>> getAllBooksByGenre(@RequestParam("genre") String genre) {
        List<Book> books = bookService.findByGenre(genre);
        return (books.isEmpty()) ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(books, OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id") Integer id) {
        return (bookService.delete(id)==1) ? new ResponseEntity<>(OK) : new ResponseEntity<>(NOT_FOUND);
    }
}