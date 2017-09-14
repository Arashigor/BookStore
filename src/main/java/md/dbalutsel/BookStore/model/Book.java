package md.dbalutsel.BookStore.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "char(30)")
    @Length(max = 30)
    @NotEmpty(message = "Name shouldn't be empty!")
    private String name;

    @Column(name = "author", columnDefinition = "char(30)")
    @Length(max = 30)
    @NotEmpty(message = "Author shouldn't be empty!")
    private String author;

    @Column(name = "year")
    @Range(max = 2050)
    @NotNull(message = "Year shouldn't be null!")
    private Integer year;

    public Book() {
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
}
