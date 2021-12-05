package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a User controller class which has one base urls /users
//                       which allows the user to register into the application . The main
//                       concept of registration process is that the whole process of registration
//                       is done through Spring Security for better security purposes and pass Encryption
//



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public String getUsers()
    {
        return "user";
    }

    @RequestMapping(value = ("/do_register"), method = {RequestMethod.POST , RequestMethod.GET})
    public String registerUser(@RequestParam("profileImage") MultipartFile file , User user , Model model , HttpSession session)
    {
        try {
            if (file.isEmpty()) {
                user.setPhoto("defaultPic.jpeg");
            } else {
                user.setPhoto(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/img").getFile();
                Files.copy(file.getInputStream(), Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }

            if(userRepository.findByUsername(user.getUsername()) !=null)
            {
                session.setAttribute("message",new MessageDto("user is already registered with this name! Try different" , "alert-danger"));
                return "register";
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.addUsers(user);
            model.addAttribute("user" , new User());
            session.setAttribute("message",new MessageDto("user have been registered !" , "alert-warning"));

            return "register";

        }catch (Exception e)
        {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message",new MessageDto("Something went wrong !" , "alert-danger"));

            return "register";
        }

    }
}
