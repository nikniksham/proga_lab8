package com.example.proga_lab8;

import com.example.proga_lab8.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}