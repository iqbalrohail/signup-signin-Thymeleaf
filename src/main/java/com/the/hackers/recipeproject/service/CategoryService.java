package com.the.hackers.recipeproject.service;


import com.the.hackers.recipeproject.data.transfer.object.Category;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HttpSession session;

    public Category helperAddCategories(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategories(Category category) {
        try {
            helperAddCategories(category);
            session.setAttribute("message", new MessageDto("Category details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding category details!", "alert-danger"));
        }
    }
}
