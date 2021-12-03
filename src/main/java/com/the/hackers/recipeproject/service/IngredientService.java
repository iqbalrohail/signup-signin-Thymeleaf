package com.the.hackers.recipeproject.service;


import com.the.hackers.recipeproject.data.transfer.object.Category;
import com.the.hackers.recipeproject.data.transfer.object.Ingredient;
import com.the.hackers.recipeproject.data.transfer.object.Ingredient;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
//        * Description: This is a Ingredient service class which contains the actual business logic for
//                       adding and getting the ingredients details using JPA methods



@Service
public class IngredientService {


    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private HttpSession session;

    public Ingredient helperAddIngredients(Ingredient ingredient) {
        return ingredientRepository.saveAndFlush(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient helperUpdateingredient(Ingredient ingredient) {
        return ingredientRepository.saveAndFlush(ingredient);
    }

    public void addIngredients(Ingredient ingredient) {
        try {
            helperAddIngredients(ingredient);
            session.setAttribute("message", new MessageDto("Ingredient details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding ingredient details!", "alert-danger"));
        }
    }

    public void updateIngredients(Ingredient ingredient) {
        try {
            helperUpdateingredient(ingredient);
            session.setAttribute("message", new MessageDto("ingredient details have been Uploaded !", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("message", new MessageDto("Error uploading ingredient details ! !", "alert-danger"));
        }
    }

    public Optional<Ingredient> updateIngredientById(long id) {
        return this.ingredientRepository.findById(id);
    }

    public void deleteIngredients(Long id)
    {
        try {

            Ingredient ingredient =this.ingredientRepository.findById(id).get();
            this.ingredientRepository.delete(ingredient);
            session.setAttribute("message", new MessageDto("Ingredient details have been deleted !", "alert-danger"));
        }catch (Exception e)
        {
            session.setAttribute("message", new MessageDto("Error deleting Ingredient !", "alert-danger"));


        }
    }




}
