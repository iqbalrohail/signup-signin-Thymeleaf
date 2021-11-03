package com.the.hackers.recipeproject.repository;

import com.the.hackers.recipeproject.data.transfer.object.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe , Integer> {
}
