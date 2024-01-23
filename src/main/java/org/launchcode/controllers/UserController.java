package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verifyPassword) {
        if (user.getPassword().equals(verifyPassword)) {
            model.addAttribute("welcomeMessage", "Welcome " + user.getUsername() + "!");
            return "user/index";
        } else {
            model.addAttribute("passwordMismatch", true);
            model.addAttribute("user", user);
            return "user/add";
        }
    }
}

