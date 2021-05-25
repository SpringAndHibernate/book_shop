package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.BookRepository;
import by.bsuir.book_shop.dao.CartRepository;
import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.OrderBook;
import by.bsuir.book_shop.entity.reg.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<OrderBook> getOrderBooks(ApplicationUser user){

        return cartRepository.findOrderBookByUser(user);
    }

    public void saveOrder(OrderBook orderBook){

        cartRepository.save(orderBook);
    }
}
