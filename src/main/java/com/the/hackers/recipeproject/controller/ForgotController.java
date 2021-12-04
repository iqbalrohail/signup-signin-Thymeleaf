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

@Controller
public class ForgotController {

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


    @PostMapping("/sendOTP")
    public String sendOtp(@RequestParam("username") String username , @RequestParam("email") String email , HttpSession session)
    {
        Random random = new Random(1000);
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
            return "redirect:/settings";
        }
        session.setAttribute("message", new MessageDto("Invalid OTP  !", "alert-danger"));

        return "redirect:/forgot";

    }


}
