package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.reg.RegisterRequest;
import by.bsuir.book_shop.service.BookService;
import by.bsuir.book_shop.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    private final BookService bookService;

    @Autowired
    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAdminPanel(Model model){

        return getAllBooks(1,model);
    }

    @GetMapping("/page/{pageNo}")
    public String getAllBooks(@PathVariable("pageNo") int pageNo, Model model){

        int pageSize = 4;
        Page<Book> page = bookService.getAllBooks(pageNo,pageSize);

        List<Book> books = page.getContent();

        model.addAttribute("books",books);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalBooks",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        return "admin_panel";
    }
}
