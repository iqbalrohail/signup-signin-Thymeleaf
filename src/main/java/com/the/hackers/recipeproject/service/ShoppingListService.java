package com.the.hackers.recipeproject.service;

import com.the.hackers.recipeproject.data.transfer.object.ShoppingList;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {


    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private HttpSession session;

    public ShoppingList helperAddShoppingLists(ShoppingList shoppingList) {
        return shoppingListRepository.saveAndFlush(shoppingList);
    }

    public ShoppingList helperUpdateshoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.saveAndFlush(shoppingList);
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public void addShoppingLists(ShoppingList shoppingList) {
        try {
            helperAddShoppingLists(shoppingList);
            session.setAttribute("message", new MessageDto("ShoppingList details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding shoppingList details!", "alert-danger"));
        }
    }

    public void updateShoppingLists(ShoppingList shoppingList) {
        try {
            helperUpdateshoppingList(shoppingList);
            session.setAttribute("message", new MessageDto("shoppingList details have been Uploaded !", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("message", new MessageDto("Error uploading shoppingList details ! !", "alert-danger"));
        }
    }

    public Optional<ShoppingList> updateShoppingListById(int id) {
        return this.shoppingListRepository.findById(id);

    }

    public void deleteShoppingLists(int id)
    {
        try {

            ShoppingList shoppingList =this.shoppingListRepository.findById(id).get();
            this.shoppingListRepository.delete(shoppingList);
            session.setAttribute("message", new MessageDto("ShoppingList details have been deleted !", "alert-danger"));
        }catch (Exception e)
        {
            session.setAttribute("message", new MessageDto("Error deleting ShoppingList !", "alert-danger"));


        }
    }
}
