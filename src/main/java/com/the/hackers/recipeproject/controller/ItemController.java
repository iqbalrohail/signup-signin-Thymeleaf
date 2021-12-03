package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.Item;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/items")
    public String getItems(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        List<Item> itemList = this.itemService.getItems();
        model.addAttribute("user", user);
        model.addAttribute("items", itemList);
        return "item";
    }

    @PostMapping("/addItems")
    public String addItems(Item item) {
        this.itemService.addItems(item);
        return "redirect:/items";
    }

    @GetMapping("/updateItemById")
    @ResponseBody
    public Optional<Item> updateItemById(int id) {
        return this.itemService.updateItemById(id);
    }

    @RequestMapping(value = "/updateItems", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateItems(Item item) {
        this.itemService.updateItems(item);
        return "redirect:/items";
    }

    @RequestMapping(value = "/deleteItems/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteContact(@PathVariable("id") int id) {
        this.itemService.deleteItems(id);
        return "redirect:/items";
    }

}
