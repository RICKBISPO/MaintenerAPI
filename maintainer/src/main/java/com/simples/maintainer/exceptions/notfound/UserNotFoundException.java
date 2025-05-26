package com.simples.maintainer.exceptions.notfound;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("user not found");
    }
}
