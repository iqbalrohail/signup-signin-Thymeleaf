package com.the.hackers.recipeproject.service;

import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.data.transfer.object.UserPrincipal;
import com.the.hackers.recipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a MyUserDetails service class which implements UserDetailsService methods
//                       of spring security to find which user is logged into the application and whether
//                       a user is authentic or not.
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("can't find the user!");
        }
        return new UserPrincipal(user);
    }
}
