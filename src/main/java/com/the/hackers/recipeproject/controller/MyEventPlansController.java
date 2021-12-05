package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.MyEventPlans;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.MyEventPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
//        * Description: This is a MyEventPlans controller class which has the base urls for performing CRUD
//                       operations on MyEventPlans module.
//


@Controller
public class MyEventPlansController {

        @Autowired
        private MyEventPlansService myEventPlansService;

        @Autowired
        private UserRepository userRepository;


        @GetMapping("/myEventPlanss")
        public String getMyEventPlanss(Model model, Principal principal) {
            String currentUserName = principal.getName();
            User user = userRepository.findByUsername(currentUserName);
            List<MyEventPlans> myEventPlansList = this.myEventPlansService.getMyEventPlanss();
            model.addAttribute("user", user);
            model.addAttribute("myEventPlanss", myEventPlansList);
            return "myEventPlans";
        }

        @PostMapping("/addMyEventPlanss")
        public String addMyEventPlanss(MyEventPlans myEventPlans) {
            this.myEventPlansService.addMyEventPlanss(myEventPlans);
            return "redirect:/myEventPlanss";
        }



        @GetMapping("/updateMyEventPlansById")
        @ResponseBody
        public Optional<MyEventPlans> updateMyEventPlansById(int id) {
            return this.myEventPlansService.updateMyEventPlansById(id);
        }

        @RequestMapping(value = "/updateMyEventPlanss", method = {RequestMethod.PUT, RequestMethod.GET})
        public String updateMyEventPlanss(MyEventPlans myEventPlans) {
            this.myEventPlansService.updateMyEventPlanss(myEventPlans);
            return "redirect:/myEventPlanss";
        }

        @RequestMapping(value = "/deleteMyEventPlanss/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
        public String deleteContact(@PathVariable("id") int id) {
            this.myEventPlansService.deleteMyEventPlanss(id);
            return "redirect:/myEventPlanss";
        }

    }
