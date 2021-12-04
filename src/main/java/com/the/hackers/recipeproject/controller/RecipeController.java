package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.Category;
import com.the.hackers.recipeproject.data.transfer.object.Recipe;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.RecipeRepository;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.CategoryService;
import com.the.hackers.recipeproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a Recipe controller class which has two base urls /recipees and
//                       /addRecipies which return a recipe.html page in the template section
//                       so as the Recipe module have @ManyToOne mapping with category module
//                       so it is necessary to add a category detail first before adding a recipe detail.


@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping( "/recipies")
    public String getRecipies(Model model , Principal principal) {
        String currentUserName =principal.getName();
        User user =  userRepository.findByUsername(currentUserName);
        List<Recipe> recipeList = this.recipeService.getRecipies();
        List<Category> categories = this.categoryService.getCategories();
        model.addAttribute("user" , user);
        model.addAttribute("recipies", recipeList);
        model.addAttribute("categories", categories);
        return "recipe";
    }

    @GetMapping( "/favRecipies")
    public String getByFav(Model model , Principal principal) {
        String currentUserName =principal.getName();
        User user =  userRepository.findByUsername(currentUserName);
        List<Recipe> recipeList = this.recipeService.getByFav();
        model.addAttribute("user" , user);
        model.addAttribute("recipies", recipeList);
        return "favRecipe";
    }



    @PostMapping("/addRecipies")
    public String addRecipies(Recipe recipe) {
        this.recipeService.addRecipies(recipe);
        return "redirect:/recipies";
    }

    @GetMapping("/recipe-detail/{recipeId}")
    public String showParticularContact(@PathVariable("recipeId") int recipeId, Model model , Principal principal) {
        String currentUserName =principal.getName();
        User user =  userRepository.findByUsername(currentUserName);
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(recipeId);
        List<Category> categories = this.categoryService.getCategories();
        Recipe recipe = recipeOptional.get();
        model.addAttribute("categories", categories);
        model.addAttribute("user" , user);
        model.addAttribute("recipe", recipe);
        return "recipeDetails";
    }

}
