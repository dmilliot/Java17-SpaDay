package org.launchcode.controllers;

import jakarta.validation.Valid;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(@Valid User user,
                                     Errors errors,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam(required = false) String email,
                                     @RequestParam("verify") String verify,
                                     Model model) {
        if(errors.hasErrors()) {
            return "user/add";
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if (user.getPassword().equals(verify)) {
            return "user/index";
        }
        else {
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }

    }


}