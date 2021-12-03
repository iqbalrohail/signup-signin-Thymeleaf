package com.the.hackers.recipeproject.service;

import com.the.hackers.recipeproject.data.transfer.object.MyEventPlans;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.MyEventPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class MyEventPlansService {

    @Autowired
    private MyEventPlansRepository myEventPlansRepository;

    @Autowired
    private HttpSession session;

    public MyEventPlans helperAddMyEventPlanss(MyEventPlans myEventPlans) {
        return myEventPlansRepository.saveAndFlush(myEventPlans);
    }

    public MyEventPlans helperUpdatemyEventPlans(MyEventPlans myEventPlans) {
        return myEventPlansRepository.saveAndFlush(myEventPlans);
    }

    public List<MyEventPlans> getMyEventPlanss() {
        return myEventPlansRepository.findAll();
    }

    public void addMyEventPlanss(MyEventPlans myEventPlans) {
        try {
            helperAddMyEventPlanss(myEventPlans);
            session.setAttribute("message", new MessageDto("MyEventPlans details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding myEventPlans details!", "alert-danger"));
        }
    }

    public void updateMyEventPlanss(MyEventPlans myEventPlans) {
        try {
            helperUpdatemyEventPlans(myEventPlans);
            session.setAttribute("message", new MessageDto("myEventPlans details have been Uploaded !", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("message", new MessageDto("Error uploading myEventPlans details ! !", "alert-danger"));
        }
    }

    public Optional<MyEventPlans> updateMyEventPlansById(int id) {
        return this.myEventPlansRepository.findById(id);

    }

    public void deleteMyEventPlanss(int id)
    {
        try {

            MyEventPlans myEventPlans =this.myEventPlansRepository.findById(id).get();
            this.myEventPlansRepository.delete(myEventPlans);
            session.setAttribute("message", new MessageDto("MyEventPlans details have been deleted !", "alert-danger"));
        }catch (Exception e)
        {
            session.setAttribute("message", new MessageDto("Error deleting MyEventPlans !", "alert-danger"));


        }
    }


}
