package com.omnicuris.items.api.app.service;

import com.omnicuris.items.api.app.domain.Item;
import com.omnicuris.items.api.app.exception.ItemAlreadyExistsException;
import com.omnicuris.items.api.app.exception.ItemNotFoundException;
import com.omnicuris.items.api.app.transaction.ItemTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemTransaction transaction;

    @Autowired
    public ItemService(ItemTransaction transaction) {
        this.transaction = transaction;
    }


    public List<Item> getAllItems() {

        return transaction.getAllItems();
    }

    public Optional<Item> getItem (String itemId) throws ItemNotFoundException {

        return transaction.getItem(itemId);

    }


    public void addItemsInBulk (List<Item> itemList) {
        transaction.addItemsInBulk(itemList);

    }


    public void addItems (Item item) throws ItemAlreadyExistsException {

        transaction.addItem(item);
    }


    public void updetItem (Item item) throws ItemNotFoundException {

        transaction.updetItem(item);
    }


    public void deleteItem (String itemId) throws ItemNotFoundException {
        transaction.deleteItem(itemId);
    }
}
