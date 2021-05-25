package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.Category;
import by.bsuir.book_shop.service.BookService;
import by.bsuir.book_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public CategoryController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "redirect:/";
    }

    @GetMapping(path = "/{id}")
    public String categoryPage(@PathVariable("id") Long id,Model model){
        return getCategory(id,1,model);
    }

    @GetMapping(path = "/{id}/page/{pageNo}")
    public String getCategory(@PathVariable("id") Long id, @PathVariable("pageNo") int pageNo,Model model){

        int pageSize = 4;

        Category category = categoryService.getCategory(id).get();

        Page<Book> page = bookService.getBooksByCategory(pageNo, pageSize, id);
        List<Book> books = page.getContent();

        model.addAttribute("category_id",id);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalBooks",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("books",books);
        model.addAttribute("category",category);
        model.addAttribute("categories",categoryService.getAllCategories());
        return "category";
    }

    @GetMapping("/new")
    public String addCategoryForm(@ModelAttribute("category") Category category,Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "category_form";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "category_form";
        }
        categoryService.addCategory(category);
        return "redirect:/category/new";
    }

    @GetMapping("/edit/{id}")
    public String updateCategoryForm(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        model.addAttribute("category",categoryService.getCategory(id));
        return "category_update_form";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@ModelAttribute("category") @Valid Category category,
                                     BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("id",category.getId());
            return "category_update_form";
        }
        categoryService.updateCategory(category);
        return "redirect:/category/new";
    }

    @GetMapping("/remove/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/category/new";
    }
}
