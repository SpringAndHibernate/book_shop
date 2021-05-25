package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.OrderBook;
import by.bsuir.book_shop.entity.reg.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<OrderBook,Long> {

    List<OrderBook> findOrderBookByUser(ApplicationUser user);

    OrderBook findByUserAndBook(ApplicationUser user, Book book);
}
