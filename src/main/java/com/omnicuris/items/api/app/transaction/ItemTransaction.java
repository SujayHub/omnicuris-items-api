package com.omnicuris.items.api.app.transaction;

import com.omnicuris.items.api.app.domain.Item;
import com.omnicuris.items.api.app.exception.ItemAlreadyExistsException;
import com.omnicuris.items.api.app.exception.ItemNotFoundException;
import com.omnicuris.items.api.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class ItemTransaction {


    private ItemRepository repository;

    @Autowired
    public ItemTransaction(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Optional<Item> getItem (String itemId) throws ItemNotFoundException {

        if(repository.existsById(itemId)) {

            return  repository.findById(itemId);

        } else
            throw  new ItemNotFoundException();
    }

    public void addItemsInBulk (List<Item> itemList) {
        repository.saveAll(itemList);
    }

    public void addItem (Item item) throws ItemAlreadyExistsException {

        if(!repository.existsById(item.getItemId()))
            repository.save(item);
        else {
            throw new ItemAlreadyExistsException();
        }
    }

    public void updetItem (Item item) throws ItemNotFoundException {

        if (repository.existsById(item.getItemId()))
            repository.save(item);
        else {
            throw new ItemNotFoundException();
        }
    }

    public void deleteItem (String itemId) throws ItemNotFoundException {

        if (repository.existsById(itemId))
            repository.deleteById(itemId);
        else {
            throw new ItemNotFoundException();
        }
    }
}