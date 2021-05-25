package by.bsuir.book_shop.entity;

import by.bsuir.book_shop.entity.reg.ApplicationUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "OrderBook")
@Table(name = "order_book")
public class OrderBook {

    @Id
    @SequenceGenerator(name = "order_sequence",sequenceName = "order_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id",nullable = false,
            foreignKey = @ForeignKey(name = "book_order_fk"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false,
            foreignKey = @ForeignKey(name = "user_order_fk"))
    private ApplicationUser user;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    public OrderBook() {
    }

    public OrderBook(Book book, ApplicationUser user, Integer quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", quantity=" + quantity +
                '}';
    }
}
