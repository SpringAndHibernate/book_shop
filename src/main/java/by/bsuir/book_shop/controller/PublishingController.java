package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.Publishing;
import by.bsuir.book_shop.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/publishing")
public class PublishingController {

    private final PublishingService publishingService;

    @Autowired
    public PublishingController(PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    @GetMapping("/new")
    public String addPublishingForm(@ModelAttribute("publishing") Publishing publishing,Model model){
        model.addAttribute("publishings",publishingService.getAllPublishings());
        return "publishing_form";
    }

    @PostMapping("/add")
    public String addPublishing(@ModelAttribute("publishing") @Valid Publishing publishing, BindingResult bindingResult,
                                Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("publishings",publishingService.getAllPublishings());
            return "publishing_form";
        }
        publishingService.addPublishing(publishing);
        return "redirect:/publishing/new";
    }

    @GetMapping("/edit/{id}")
    public String updatePublishingForm(@PathVariable("id") Long id,Model model){

        model.addAttribute("id",id);
        model.addAttribute("publishing",publishingService.getPublishing(id));
        return "publishing_update_form";
    }

    @PostMapping("/update/{id}")
    public String updatePublishing(@ModelAttribute("publishing") @Valid Publishing publishing,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("id",publishing.getId());
            return "publishing_update_form";
        }
        publishingService.updatePublishing(publishing);
        return "redirect:/publishing/new";
    }

    @GetMapping("/remove/{id}")
    public String deletePublishing(@PathVariable("id") Long id){
        publishingService.deletePublishing(id);
        return "redirect:/publishing/new";
    }
}
