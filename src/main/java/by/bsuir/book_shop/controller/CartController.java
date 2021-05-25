package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.OrderBook;
import by.bsuir.book_shop.entity.reg.ApplicationUser;
import by.bsuir.book_shop.service.BookService;
import by.bsuir.book_shop.service.CartService;
import by.bsuir.book_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;
    private final BookService bookService;

    @Autowired
    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showCart(Model model){

        return "cart";
    }

    @GetMapping("/{id}")
    public String showCart(@ModelAttribute("order")OrderBook orderBook, @PathVariable("id") Long id, Model model){

        Book book = bookService.getBook(id).get();
        model.addAttribute("book_id",id);
        model.addAttribute("book",book);
        model.addAttribute("books", bookService.getBooks());
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String add(@ModelAttribute("order") OrderBook orderBook, @AuthenticationPrincipal ApplicationUser user, @PathVariable("id") Long id, Model model){

        Book book = bookService.getBook(id).get();
        model.addAttribute("book_id",id);
        model.addAttribute("book",book);
        orderBook.setUser(user);
        orderBook.setBook(book);
        cartService.saveOrder(orderBook);
        return "redirect:/";
    }
}
