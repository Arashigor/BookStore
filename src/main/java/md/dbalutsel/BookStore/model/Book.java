package md.dbalutsel.BookStore.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "bookstore", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.name.Length}")
    @NotEmpty(message = "{category.name.NotEmpty}")
    @NotNull(message = "{category.name.NotNull}")
    private String name;

    @Column(name = "author", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.author.Length}")
    @NotEmpty(message = "{category.author.NotEmpty}")
    @NotNull(message = "{category.author.NotNull}")
    private String author;

    @Column(name = "year")
    @Range(max = 2050, message = "{category.year.Range}")
    @NotNull(message = "{category.year.NotNull}")
    private Integer year;

    @Column(name = "genre", columnDefinition = "char(30)")
    @Length(max = 30, message = "{category.genre.Length}")
    @NotEmpty(message = "{category.genre.NotEmpty}")
    @NotNull(message = "{category.genre.NotNull}")
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
}
