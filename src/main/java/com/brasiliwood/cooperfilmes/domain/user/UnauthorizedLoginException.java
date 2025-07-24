package com.brasiliwood.cooperfilmes.domain.user;

public class UnauthorizedLoginException extends RuntimeException {

    public UnauthorizedLoginException() {
        super("Given password doesn't matches with found user password!");
    }

}
