package com.brasiliwood.cooperfilmes.domain.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("No one user was found by given email");
    }

}
