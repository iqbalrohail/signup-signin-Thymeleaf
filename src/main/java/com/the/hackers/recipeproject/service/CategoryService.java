package com.the.hackers.recipeproject.service;


import com.the.hackers.recipeproject.data.transfer.object.Category;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.CategoryRepository;
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
//        * Description: This is a category service class which contains the actual business logic for
//                       adding and getting the category details using JPA methods

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HttpSession session;

    public Category helperAddCategories(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public Category helperUpdatecategory(Category category) {
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

    public void updateCategories(Category category) {
        try {
            helperUpdatecategory(category);
            session.setAttribute("message", new MessageDto("category details have been Uploaded !", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("message", new MessageDto("Error uploading category details ! !", "alert-danger"));
        }
    }

    public Optional<Category> updateCategoryById(long id) {
        return this.categoryRepository.findById(id);

    }

    public void deleteCategories(Long id)
    {
        try {

            Category category =this.categoryRepository.findById(id).get();
            this.categoryRepository.delete(category);
            session.setAttribute("message", new MessageDto("Category details have been deleted !", "alert-danger"));
        }catch (Exception e)
        {
            session.setAttribute("message", new MessageDto("Error deleting Category !", "alert-danger"));


        }
    }



}
