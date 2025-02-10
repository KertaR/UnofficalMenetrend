package hu.kertar.unofficalmenetrend.controller;

import hu.kertar.unofficalmenetrend.model.User;
import hu.kertar.unofficalmenetrend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.registerUser(user);
            return "redirect:/login"; // Sikeres regisztráció után a bejelentkezési oldalra irányítjuk
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
