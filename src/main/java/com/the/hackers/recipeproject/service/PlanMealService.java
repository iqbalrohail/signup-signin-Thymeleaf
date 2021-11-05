package com.the.hackers.recipeproject.service;


import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.data.transfer.object.PlanMeal;
import com.the.hackers.recipeproject.repository.PlanMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PlanMealService {

    @Autowired
    private PlanMealRepository planMealRepository;

    @Autowired
    private HttpSession session;

    public PlanMeal helperAddPlanMeals(PlanMeal planMeal) {
        return planMealRepository.saveAndFlush(planMeal);
    }

    public List<PlanMeal> getPlanMeals() {
        return planMealRepository.findAll();
    }

    public void addPlanMeals(PlanMeal planMeal) {
        try {
            helperAddPlanMeals(planMeal);
            session.setAttribute("message", new MessageDto("PlanMeal details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding planMeal details!", "alert-danger"));
        }
    }


}

