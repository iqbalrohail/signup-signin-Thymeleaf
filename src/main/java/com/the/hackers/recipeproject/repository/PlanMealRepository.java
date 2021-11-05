package com.the.hackers.recipeproject.repository;

import com.the.hackers.recipeproject.data.transfer.object.PlanMeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanMealRepository extends JpaRepository<PlanMeal , Long> {
}
