package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Objects;
import java.util.Random;

//
//Project: COMP 3095 Recipe Assignment
//        * Assignment: 1 Web Development
//        * Author(s): Shehzad Contractor Student Number: 101285996
//        Amanda Caglioni    Student Number: 101237363
//        Rohan Khullar      Student Number: 101284533
//        Vishwa Mavani      Student Number: 101285743
//        * Date: 7th November 2021
//        * Description: This is a Forgot controller which has two base urls /forgot and
//                       /settings which returns a openPasswordForm.html and settings.html page in the template section
//                       The openPasswordForm.html page has a form for verification of an authentic user and after verification
//                       it sends a action to /sendOTP url which sends a random otp in the console. A user can get that otp and
//                       send it for otp verification, if the otp is correct then /reset-password url is called which returns a reset.html
//                       page for adding new password and then this page sends an action on /resetPass url which changes and add a new password
//                       for the correct username in the field.
//                       similarly for changing a password , a user must be logged in to the application and calls the /settings it calls
//                       settings.html page which have a change password form which sends an action to /change-password url which changes the
//                       user password by verifying from the previous password.
//


@Controller
public class ForgotController {
    Random random = new Random(1000); // generating otp

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    int otp;

    @GetMapping("/forgot")
    public String openPasswordForm()
    {

        return "openPasswordForm";
    }

    @GetMapping("/settings")
    public String changePasswordPage()
    {
        return "settings";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage()
    {
        return "reset";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword , @RequestParam("newPassword") String newPassword , Principal principal , HttpSession session)
    {
        String currentUserName = principal.getName();
        User currentUser = userRepository.findByUsername(currentUserName);

        if (bCryptPasswordEncoder.matches(oldPassword , currentUser.getPassword()))
        {

            currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.saveAndFlush(currentUser);
            session.setAttribute("message", new MessageDto("Your password have been changed !", "alert-success"));

        }
        else {

            session.setAttribute("message", new MessageDto("Error changing your password!", "alert-danger"));

        }
        return "redirect:/settings";
    }

    @PostMapping("/resetPass")
    public String resetPassword(@RequestParam("oldPassword") String oldPassword , @RequestParam("newPassword") String newPassword ,  @RequestParam("username") String username ,HttpSession session)
    {
        User currentUser = userRepository.findByUsername(username);

        if(currentUser!=null) {
            if (Objects.equals(oldPassword, newPassword)) {
                currentUser.setPassword(bCryptPasswordEncoder.encode(oldPassword));
                this.userRepository.saveAndFlush(currentUser);
                session.setAttribute("message", new MessageDto("Your password have been changed !", "alert-success"));
            }
            else {

                session.setAttribute("message", new MessageDto("Error changing your password!", "alert-danger"));

            }
        }

        else {

            session.setAttribute("message", new MessageDto("Username is not valid", "alert-danger"));

        }

        return "redirect:/reset-password";
    }


    @PostMapping("/sendOTP")
    public String sendOtp(@RequestParam("username") String username , @RequestParam("email") String email , HttpSession session)
    {
        otp = random.nextInt(99999);

        User user = userRepository.findByUsername(username);
        if(Objects.equals(user.getEmail(), email))
        {
            System.out.println("OTP : "+otp);
            session.setAttribute("message", new MessageDto("we have sucessfully send the OTP in console !", "alert-success"));
            return "verifyOtp";
        }

        session.setAttribute("message", new MessageDto("Username or email should be valid  !", "alert-danger"));
        return "verifyOtp";

    }

    @PostMapping("/verification")
    public String verification(@RequestParam("otp") int checkOtp , HttpSession session)
    {
        if(otp == checkOtp)
        {
            return "redirect:/reset-password";
        }
        session.setAttribute("message", new MessageDto("Invalid OTP  !", "alert-danger"));

        return "redirect:/forgot";

    }


}
