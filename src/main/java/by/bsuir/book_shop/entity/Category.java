package by.bsuir.book_shop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Category")
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_sequence",sequenceName = "category_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "category name should not be empty")
    @Size(min=2, max=50,message = "category name should be between 2 and 50 characters")
    private String name;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Book> books = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void addBook(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
            book.setCategory(this);
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
