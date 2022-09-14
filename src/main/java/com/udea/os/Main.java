package com.udea.os;

import com.udea.os.http.HttpServerRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        try {
            LOGGER.info("Loading JGKServer");
            HttpServerRunner httpServerRunner = new HttpServerRunner();
            httpServerRunner.start();
        } catch (Exception e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }
}