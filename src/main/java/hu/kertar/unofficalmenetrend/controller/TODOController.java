package hu.kertar.unofficalmenetrend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TODOController {

    @GetMapping("/todo")
    public String todoPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "todo";
        }
        return "redirect:/login";
    }
}
