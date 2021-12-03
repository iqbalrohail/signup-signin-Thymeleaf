package com.the.hackers.recipeproject.service;

import com.the.hackers.recipeproject.data.transfer.object.Item;
import com.the.hackers.recipeproject.data.transfer.object.MessageDto;
import com.the.hackers.recipeproject.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HttpSession session;

    public Item helperAddItems(Item item) {
        return itemRepository.saveAndFlush(item);
    }

    public Item helperUpdateitem(Item item) {
        return itemRepository.saveAndFlush(item);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addItems(Item item) {
        try {
            helperAddItems(item);
            session.setAttribute("message", new MessageDto("Item details have been added !", "alert-success"));

        } catch (Exception e) {

            session.setAttribute("message", new MessageDto("Error adding item details!", "alert-danger"));
        }
    }

    public void updateItems(Item item) {
        try {
            helperUpdateitem(item);
            session.setAttribute("message", new MessageDto("item details have been Uploaded !", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("message", new MessageDto("Error uploading item details ! !", "alert-danger"));
        }
    }

    public Optional<Item> updateItemById(int id) {
        return this.itemRepository.findById(id);

    }

    public void deleteItems(int id)
    {
        try {

            Item item =this.itemRepository.findById(id).get();
            this.itemRepository.delete(item);
            session.setAttribute("message", new MessageDto("Item details have been deleted !", "alert-danger"));
        }catch (Exception e)
        {
            session.setAttribute("message", new MessageDto("Error deleting Item !", "alert-danger"));


        }
    }
}
