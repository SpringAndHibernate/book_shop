package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Author;
import by.bsuir.book_shop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/new")
    public String addAuthorForm(@ModelAttribute("author") Author author,Model model){
        model.addAttribute("authors",authorService.getAllAuthors());
        return "author_form";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("authors",authorService.getAllAuthors());
            return "author_form";
        }
        authorService.addAuthor(author);
        return "redirect:/author/new";
    }

    @GetMapping("/edit/{id}")
    public String updateAuthorForm(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        model.addAttribute("author",authorService.getAuthor(id));
        return "author_update_form";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("id",author.getId());
            return "author_update_form";
        }
        authorService.updateAuthor(author);
        return "redirect:/author/new";
    }

    @GetMapping("/remove/{id}")
    public String deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return "redirect:/author/new";
    }
}
