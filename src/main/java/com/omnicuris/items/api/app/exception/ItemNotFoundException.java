package com.omnicuris.items.api.app.exception;

public class ItemNotFoundException extends Exception {


    @Override
    public String getMessage(){

        return "item not found";
    }

    public ItemNotFoundException() {

    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
