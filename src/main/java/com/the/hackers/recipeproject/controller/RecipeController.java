package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.Recipe;
import com.the.hackers.recipeproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipies")
    public String getRecipies(Model model) {

        List<Recipe> recipeList = this.recipeService.getRecipies();
        model.addAttribute("recipe", recipeList);
        return "recipies";
    }

    @PostMapping("/addRecipies")
    public String addRecipies(Recipe recipe) {
        this.recipeService.addRecipies(recipe);
        return "redirect:/recipies";
    }

}
