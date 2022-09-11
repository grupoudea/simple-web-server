package com.udea.os.config;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesManager {

    static private PropertiesManager propertiesManager = null;
    private final Properties properties;

    private PropertiesManager(Properties properties) {
        this.properties = properties;
    }

    static public PropertiesManager getPropertiesManager() {
        if (propertiesManager == null) {
            propertiesManager = new PropertiesManager(readProperties());
        }
        return propertiesManager;
    }

    public Properties getProperties() {
        return properties;
    }

    private static Properties readProperties() {
        System.out.println("Server is reading the configuration...");
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader("JGKServer/config/properties.json"), Properties.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}