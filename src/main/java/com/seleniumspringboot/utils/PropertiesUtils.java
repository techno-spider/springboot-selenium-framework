package com.seleniumspringboot.utils;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class PropertiesUtils {

    private final Properties properties;
    private final String filepath;

    public PropertiesUtils(String filepath) {
        this.filepath = filepath;
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filepath);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error handling Properties file: " + e.getMessage());
        }
    }

    public String getPropertyValue(String value) {
        return properties.getProperty(value);
    }

    public void setPropertyValue(String key, String value) {
        try {
            FileOutputStream outputStream = new FileOutputStream(filepath);
            properties.setProperty(key, value);
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing value into Properties file: " + e.getMessage());
        }
    }
}
