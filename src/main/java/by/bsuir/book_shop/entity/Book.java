package by.bsuir.book_shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity(name = "Book")
@Table(name = "book",uniqueConstraints = {@UniqueConstraint(
        name = "book_isbn_unique",columnNames = "isbn"
)})
public class Book {

    @Id
    @SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "title",nullable = false)
    @NotBlank(message = "title should not be empty")
    @Size(min=2, max=50,message = "title should be between 2 and 50 characters")
    private String title;

    @Column(name = "isbn",nullable = false)
    @NotBlank(message = "isbn should not be empty")
    @Size(min=2, max=50,message = "isbn should be between 2 and 50 characters")
    private String isbn;

    @Column(name = "cover",nullable = false)
    @NotBlank(message = "cover should not be empty")
    @Size(min=2, max=50,message = "cover should be between 2 and 50 characters")
    private String cover;

    @Column(name = "age_limit",nullable = false)
    @NotBlank(message = "age limit should not be empty")
    @Size(min=2, max=50,message = "age limit should be between 2 and 50 characters")
    private String ageLimit;

    @Column(name = "pages",nullable = false)
    @Min(value = 2,message = "pages count should be more than 1")
    private Integer pages;

    @Column(name = "weight",nullable = false)
    @Min(value = 101,message = "weight should be more than 100")
    private Double weight;

    @Column(name = "rating",nullable = false)
    private Integer rating;

    @Column(name = "format",nullable = false)
    @NotBlank(message = "format should not be empty")
    @Size(min=2, max=50,message = "format should be between 2 and 50 characters")
    private String format;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability",nullable = false)
    private Availability availability;

    @Column(name = "release_date",nullable = false)
    private Integer releaseDate;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_id",referencedColumnName = "id",nullable = false,
            foreignKey = @ForeignKey(name = "book_publishing_fk"))
    private Publishing publishing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = false,
            foreignKey = @ForeignKey(name = "book_category_fk"))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",referencedColumnName = "id",nullable = false,
            foreignKey = @ForeignKey(name = "book_author_fk"))
    private Author author;

    private String image;

    public Book() {
    }

    public Book(String title, String isbn, String cover, String ageLimit, Integer pages,
                Double weight, Integer rating, String format, Availability availability,
                Integer releaseDate, BigDecimal price) {
        this.title = title;
        this.isbn = isbn;
        this.cover = cover;
        this.ageLimit = ageLimit;
        this.pages = pages;
        this.weight = weight;
        this.rating = rating;
        this.format = format;
        this.availability = availability;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishing() {
        return publishing.getName();
    }

    public void setPublishing(Publishing publishing) {
        this.publishing = publishing;
    }

    public String getAuthor() {
        return author.getName() + " " + author.getSurname();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCategory() {
        return category.getName();
    }

    @JsonIgnore
    public Category getCategoryName() {
        return category;
    }

    @JsonIgnore
    public Publishing getPublishingName() {
        return publishing;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", cover='" + cover + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", pages=" + pages +
                ", weight=" + weight +
                ", rating=" + rating +
                ", format='" + format + '\'' +
                ", availability=" + availability +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                ", publishing=" + publishing +
                ", category=" + category +
                ", author=" + author +
                ", image='" + image + '\'' +
                '}';
    }
}
