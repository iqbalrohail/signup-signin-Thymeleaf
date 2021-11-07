package com.the.hackers.recipeproject.controller;


import com.the.hackers.recipeproject.data.transfer.object.PlanMeal;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.PlanMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
//        * Description: This is a PlanMeal controller class which has two base urls /planMeals and
//                       /addPlanMeals which return an planMeal.html page in the template section
//                       so as in the PlanMeal , user can add his/her weekly plan or to-do list of the recipe
//
@Controller
public class PlanMealController {

    @Autowired
    private PlanMealService planMealService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/planMeals")
    public String getPlanMeals(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        List<PlanMeal> planMealList = this.planMealService.getPlanMeals();
        model.addAttribute("user", user);
        model.addAttribute("planMeals", planMealList);
        return "planMeal";
    }

    @PostMapping("/addPlanMeals")
    public String addPlanMeals(PlanMeal planMeal) {
        this.planMealService.addPlanMeals(planMeal);
        return "redirect:/planMeals";
    }

}



