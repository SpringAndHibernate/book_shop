package by.bsuir.book_shop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Author")
@Table(name = "author")
public class Author {

    @Id
    @SequenceGenerator(name = "author_sequence",sequenceName = "author_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "author name should not be empty")
    @Size(min=2, max=50,message = "author name should be between 2 and 50 characters")
    private String name;

    @Column(name = "surname",nullable = false)
    @NotBlank(message = "author surname should not be empty")
    @Size(min=2, max=50,message = "author surname should be between 2 and 50 characters")
    private String surname;

    @Column(name = "biography")
    @NotBlank(message = "biography should not be empty")
    @Size(min=10,message = "biography should hav 10 characters")
    private String biography;

    @OneToMany(mappedBy = "author",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, String surname, String biography) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }

    public void addBook(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
            book.setAuthor(this);
        }
    }

    public void deleteBook(Book book){
        if (this.books.contains(book)){
            this.books.remove(book);
            book.setCategory(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", biography='" + biography + '\'' +
                ", books=" + books +
                '}';
    }
}
