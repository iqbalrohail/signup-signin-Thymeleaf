package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class View {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/index"})
    public String view(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("message", new MessageDto("you have been logged out !", "alert-danger"));
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Model model , Principal principal) {

        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        model.addAttribute("user", user);
        return "profile";
    }

}