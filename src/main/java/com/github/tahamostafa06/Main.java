package com.github.tahamostafa06;

import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        System.out.println(AuthenticationHelper.sha256("Mazen"));
    }
}