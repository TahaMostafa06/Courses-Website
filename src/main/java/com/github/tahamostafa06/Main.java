package com.github.tahamostafa06;

import com.github.tahamostafa06.backend.Server;
import com.github.tahamostafa06.backend.auth.AuthenticationHelper;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = Server.getServer();
            System.err.println("Hello");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}