package com.the.hackers.recipeproject.controller;

import com.the.hackers.recipeproject.data.transfer.object.Item;
import com.the.hackers.recipeproject.data.transfer.object.ShoppingList;
import com.the.hackers.recipeproject.data.transfer.object.User;
import com.the.hackers.recipeproject.repository.UserRepository;
import com.the.hackers.recipeproject.service.ItemService;
import com.the.hackers.recipeproject.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingListController {


    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/shoppingLists")
    public String getShoppingLists(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User user = userRepository.findByUsername(currentUserName);
        List<ShoppingList> shoppingList = this.shoppingListService.getShoppingLists();
        List<Item> items = this.itemService.getItems();
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        model.addAttribute("shoppingLists", shoppingList);
        return "shoppingList";
    }

    @PostMapping("/addShoppingLists")
    public String addShoppingLists(ShoppingList shoppingList) {
        this.shoppingListService.addShoppingLists(shoppingList);
        return "redirect:/shoppingLists";
    }

    @GetMapping("/updateShoppingListById")
    @ResponseBody
    public Optional<ShoppingList> updateShoppingListById(int id) {
        return this.shoppingListService.updateShoppingListById(id);
    }

    @RequestMapping(value = "/updateShoppingLists", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateShoppingLists(ShoppingList shoppingList) {
        this.shoppingListService.updateShoppingLists(shoppingList);
        return "redirect:/shoppingLists";
    }

    @RequestMapping(value = "/deleteShoppingLists/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteContact(@PathVariable("id") int id) {
        this.shoppingListService.deleteShoppingLists(id);
        return "redirect:/shoppingLists";
    }


}
