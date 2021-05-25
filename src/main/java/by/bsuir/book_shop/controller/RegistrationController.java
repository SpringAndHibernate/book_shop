package by.bsuir.book_shop.controller;

import by.bsuir.book_shop.entity.reg.RegisterRequest;
import by.bsuir.book_shop.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/reg")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistrationView(@ModelAttribute("request") RegisterRequest registerRequest){
        return "reg";
    }

    @PostMapping
    public String register(@ModelAttribute("request") @Valid RegisterRequest registerRequest, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "reg";
        }
        registrationService.register(registerRequest);
        return "redirect:/";
    }

    @GetMapping(path = "/admin")
    public String getAdminRegistrationView(@ModelAttribute("request") RegisterRequest registerRequest){
        return "admin_reg";
    }

    @PostMapping(path = "/admin")
    public String registerAdmin(@ModelAttribute("request") @Valid RegisterRequest registerRequest, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "admin_reg";
        }
        registrationService.registerAdmin(registerRequest);
        return "redirect:/";
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "login";
    }
}
