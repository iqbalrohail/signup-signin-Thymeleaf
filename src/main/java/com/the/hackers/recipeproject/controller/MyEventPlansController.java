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
