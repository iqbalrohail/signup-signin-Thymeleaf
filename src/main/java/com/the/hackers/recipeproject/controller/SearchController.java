package com.the.hackers.recipeproject.controller;


import com.the.hackers.recipeproject.data.transfer.object.Recipe;

import com.the.hackers.recipeproject.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class SearchController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchRecipe(@PathVariable("query") String query) {
        List<Recipe> recipeList = this.recipeRepository.findByDescriptionContaining(query);
        return ResponseEntity.ok(recipeList);
    }
}