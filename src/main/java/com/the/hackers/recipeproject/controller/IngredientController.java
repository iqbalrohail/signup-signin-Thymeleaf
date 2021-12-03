package com.the.hackers.recipeproject.controller;


import com.the.hackers.recipeproject.data.transfer.object.Ingredient;
import com.the.hackers.recipeproject.data.transfer.object.Ingredient;
import com.the.hackers.recipeproject.data.transfer.object.Recipe;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.IngredientService;
import com.the.hackers.recipeproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        * Description: This is a Ingredient controller class which has two base urls /ingredients and
//                       /addIngredients which return an ingredient.html page in the template section
//                       so as the Ingredient module have @ManyToMany mapping with recipe module
//                       so it is necessary to add a recipe detail first before adding an ingredient.



@Controller
public class IngredientController {


    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/ingredients")
    public String getIngredients(Model model , Principal principal) {
        String currentUserName =principal.getName();
        User user =  userRepository.findByUsername(currentUserName);
        List<Ingredient> ingredientList = this.ingredientService.getIngredients();
        List<Recipe> recipes = this.recipeService.getRecipies();
        model.addAttribute("user" , user);
        model.addAttribute("ingredients", ingredientList);
        model.addAttribute("recipies" , recipes);
        return "ingredient";
    }

    @PostMapping("/addIngredients")
    public String addIngredients(Ingredient ingredient) {
        this.ingredientService.addIngredients(ingredient);
        return "redirect:/ingredients";
    }


    @GetMapping("/updateIngredientById")
    @ResponseBody
    public Optional<Ingredient> updateIngredientById(int id) {
        return this.ingredientService.updateIngredientById(id);
    }

    @RequestMapping(value = "/updateIngredients", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateIngredients(Ingredient ingredient) {
        this.ingredientService.updateIngredients(ingredient);
        return "redirect:/Ingredients";
    }

    @RequestMapping(value = "/deleteIngredients/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteContact(@PathVariable("id") long id) {
        this.ingredientService.deleteIngredients(id);
        return "redirect:/Ingredients";
    }





}

