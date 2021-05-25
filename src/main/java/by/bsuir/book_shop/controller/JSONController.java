package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/json")
public class JSONController {

    private final BookService bookService;

    @Autowired
    public JSONController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){

        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id){

        return bookService.getBook(id);
    }
}
