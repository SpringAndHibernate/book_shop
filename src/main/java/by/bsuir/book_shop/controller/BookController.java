package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.*;
import by.bsuir.book_shop.service.AuthorService;
import by.bsuir.book_shop.service.BookService;
import by.bsuir.book_shop.service.CategoryService;
import by.bsuir.book_shop.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "/")
public class BookController {

    @Value("${upload/path}")
    private String uploadPath;

    private final BookService bookService;
    private final CategoryService categoryService;
    private final PublishingService publishingService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, PublishingService publishingService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.publishingService = publishingService;
        this.authorService = authorService;
    }

    @GetMapping
    public String homePage(Model model){
        return getAllBooks(1,model);
    }

    @GetMapping("/page/{pageNo}")
    public String getAllBooks(@PathVariable("pageNo") int pageNo, Model model){

        int pageSize = 9;
        Page<Book> page = bookService.getAllBooks(pageNo,pageSize);

        List<Book> books = page.getContent();

        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("books",books);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalBooks",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        return "index";
    }

    @GetMapping(path = "/login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") Long id, Model model){
        Optional<Book> book = bookService.getBook(id);
        List<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book",res);
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book";
    }

    @GetMapping("/find")
    public String findBooks(@RequestParam String q, Model model){

        List<Book> books = bookService.findBooks(q);
        model.addAttribute("books",books);
        return "index";
    }

    @GetMapping("/book/new")
    public String addBookForm(@ModelAttribute("book") Book book,Model model){

        model.addAttribute("authors",authorService.getAllAuthors());
        model.addAttribute("publishings",publishingService.getAllPublishings());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("availability", Availability.values());
        return "book_form";
    }

    @PostMapping(path = "/add")
    public String addBook(@ModelAttribute("book") @Valid Book book,@RequestParam("file") MultipartFile file,
                          BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()){

            model.addAttribute("authors",authorService.getAllAuthors());
            model.addAttribute("publishings",publishingService.getAllPublishings());
            model.addAttribute("categories",categoryService.getAllCategories());
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("availability", Availability.values());
            return "book_form";
        }


        if (file != null){

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String resultFile = uuid + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFile));

            book.setImage(resultFile);
        }

        bookService.addBook(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id",id);
        model.addAttribute("book",bookService.getBook(id));
        model.addAttribute("authors",authorService.getAllAuthors());
        model.addAttribute("publishings",publishingService.getAllPublishings());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("availability", Availability.values());
        return "book_update_form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("id",book.getId());
            model.addAttribute("authors",authorService.getAllAuthors());
            model.addAttribute("publishings",publishingService.getAllPublishings());
            model.addAttribute("categories",categoryService.getAllCategories());
            model.addAttribute("books", bookService.getBooks());
            model.addAttribute("availability", Availability.values());
            return "book_update_form";
        }
        bookService.updateBook(book);
        return "redirect:/book/new";
    }

    @GetMapping("/remove/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/book/new";
    }

}
