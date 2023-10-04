package com.projetcloud.exceptions;

public class LoginAlreadyUsedException extends Exception{

    public LoginAlreadyUsedException(String message) {
        super(message);
    }
}
