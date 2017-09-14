package md.dbalutsel.BookStore.repo;

import md.dbalutsel.BookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}