package com.spring_mongodb.exception;

import lombok.ToString;

@ToString
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException() {
        super();
    }

}
