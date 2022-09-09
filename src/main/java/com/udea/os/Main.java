package com.udea.os;

import com.udea.os.config.Properties;
import com.udea.os.config.PropertiesManager;
import com.udea.os.http.HttpProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final Properties properties = PropertiesManager.getPropertiesManager().getProperties();

    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(properties.getPort());
            System.out.println("Server started.\nListening for connections on port : " + properties.getPort() + " ...\n");
            do {
                Socket connect = serverConnect.accept();
                Thread thread = new Thread(new HttpProcessor(connect));
                thread.start();
            } while (!serverConnect.isClosed());
        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }
}