package com.the.hackers.recipeproject.service;

import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a User service class which contains the actual business logic for
//                       registering a user using JPA and Spring security implementation
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUsers(User user)
    {
        userRepository.saveAndFlush(user);
    }



}
