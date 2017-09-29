package md.dbalutsel.bookstore.model;

import md.dbalutsel.bookstore.model.validators.Unique;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "bookstore", name = "books")
@Proxy(lazy = false)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.books.name.Length}")
    @NotEmpty(message = "{category.books.name.NotEmpty}")
    @NotNull(message = "{category.books.name.NotNull}")
    @Unique(message = "{category.books.name.Unique}", entityType = Book.class, property = "name")
    private String name;

    @Column(name = "author", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.books.author.Length}")
    @NotEmpty(message = "{category.books.author.NotEmpty}")
    @NotNull(message = "{category.books.author.NotNull}")
    private String author;

    @Column(name = "year")
    @Range(max = 2050, message = "{category.books.year.Range}")
    @NotNull(message = "{category.books.year.NotNull}")
    private Integer year;

    @Column(name = "genre", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.books.genre.Length}")
    @NotEmpty(message = "{category.books.genre.NotEmpty}")
    @NotNull(message = "{category.books.genre.NotNull}")
    private String genre;

    public Book() {
    }

    public Book(Integer id, String name, String author, Integer year, String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public Book(String name, String author, Integer year, String genre) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getId() != null ? !getId().equals(book.getId()) : book.getId() != null) return false;
        if (getName() != null ? !getName().equals(book.getName()) : book.getName() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getYear() != null ? !getYear().equals(book.getYear()) : book.getYear() != null) return false;
        return getGenre() != null ? getGenre().equals(book.getGenre()) : book.getGenre() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }
}
