package com.the.hackers.recipeproject.controller;


import com.the.hackers.recipeproject.data.transfer.object.Ingredient;
import com.the.hackers.recipeproject.data.transfer.object.Recipe;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.IngredientService;
import com.the.hackers.recipeproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

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

}

