package com.github.tahamostafa06.backend.auth.Exceptions;

public class IncorrectPassword extends LoginException{
    public IncorrectPassword(String message) {
        super(message);
    }
}