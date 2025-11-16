package com.github.tahamostafa06.backend.auth.Exceptions;

public class UserNotFound extends LoginException {
    public UserNotFound(String message) {
        super(message);
    }
}