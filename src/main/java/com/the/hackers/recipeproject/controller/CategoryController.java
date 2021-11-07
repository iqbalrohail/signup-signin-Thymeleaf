package com.the.hackers.recipeproject.controller;


import com.the.hackers.recipeproject.data.transfer.object.Category;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.OneToMany;
import java.security.Principal;
import java.util.List;
//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a category controller file which has two base urls /categories and
//                       /addCategories which return a category.html page in the template section
//                       so as the Category module have @OneToMany mapping with recipe module
//                       so it is necessary to add a category first before adding a recipe.



@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/categories")
    public String getCategories(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        List<Category> categoryList = this.categoryService.getCategories();
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryList);
        return "category";
    }

    @PostMapping("/addCategories")
    public String addCategories(Category category) {
        this.categoryService.addCategories(category);
        return "redirect:/categories";
    }

}


