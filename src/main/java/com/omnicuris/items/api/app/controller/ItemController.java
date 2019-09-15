package com.omnicuris.items.api.app.controller;

import com.omnicuris.items.api.app.domain.Item;
import com.omnicuris.items.api.app.exception.ItemAlreadyExistsException;
import com.omnicuris.items.api.app.exception.ItemNotFoundException;
import com.omnicuris.items.api.app.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ItemController {

    private ItemService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public ResponseEntity<Object> getAllItems() {

        return new ResponseEntity<>(service.getAllItems(), HttpStatus.OK) ;
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Object> getItem(@PathVariable String itemId){


        try{
            return  service.getItem(itemId)
                    .<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("No Record Found", HttpStatus.OK));
        }catch (ItemNotFoundException e){

            LOGGER.info("No record found in database", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/items/addItem/bulkInsert")
    ResponseEntity<Object> addItems(@RequestBody List<Item> itemList) {

        service.addItemsInBulk(itemList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/items/addItem")
    ResponseEntity<Object> addItems(@RequestBody Item item)  {

        try {
            service.addItems(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ItemAlreadyExistsException e) {
            LOGGER.info("Item already exist", e);
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }

    }

    @PutMapping("/items/update")
    public ResponseEntity<Object> updetItem(@RequestBody Item item){

        try {
            service.updetItem(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ItemNotFoundException e) {
            LOGGER.info("No record found in database", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Object> deleteItem (@PathVariable String itemId){

        try {
            service.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            LOGGER.info("No record found in database", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
