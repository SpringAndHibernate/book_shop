package by.bsuir.book_shop.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Publishing")
@Table(name = "publishing",uniqueConstraints = {
        @UniqueConstraint(name = "publishing_website_unique",columnNames = "website"),
        @UniqueConstraint(name = "publishing_phone_unique",columnNames = "phone")
})
public class Publishing {

    @Id
    @SequenceGenerator(name = "publishing_sequence",sequenceName = "publishing_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "publishing_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "publishing name should not be empty")
    @Size(min=3, max=50,message = "publishing name should be between 3 and 50 characters")
    private String name;

    @Column(name = "website",nullable = false)
    @NotBlank(message = "website should not be empty")
    @Size(min=7, max=50,message = "website should be between 7 and 50 characters")
    private String website;

    @Column(name = "phone",nullable = false)
    @NotBlank(message = "phone should not be empty")
    @Size(min=12, max=50,message = "phone should be between 12 and 50 characters")
    private String phone;

    @AttributeOverrides({
            @AttributeOverride(name = "city",column = @Column(name = "city")),
            @AttributeOverride(name = "street",column = @Column(name = "street")),
            @AttributeOverride(name = "building",column = @Column(name = "building")),
            @AttributeOverride(name = "postalCode",column = @Column(name = "postal_code"))
    })
    private Address address;

    @OneToMany(mappedBy = "publishing",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Book> books = new ArrayList<>();

    public Publishing() {
    }

    public Publishing(String name, String website, String phone, Address address) {
        this.name = name;
        this.website = website;
        this.phone = phone;
        this.address = address;
    }

    public void addBook(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
            book.setPublishing(this);
        }
    }

    public void deleteBook(Book book){
        if (this.books.contains(book)){
            this.books.remove(book);
            book.setPublishing(null);
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Publishing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
