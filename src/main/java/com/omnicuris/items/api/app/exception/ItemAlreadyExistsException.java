package com.omnicuris.items.api.app.exception;

public class ItemAlreadyExistsException extends Exception {


    @Override
    public String getMessage(){

        return "item already exist";
    }

    public ItemAlreadyExistsException() {

    }

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
