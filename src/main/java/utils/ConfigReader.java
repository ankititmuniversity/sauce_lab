package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;
    static {
    	String env = System.getProperty("env","sit");
        try (FileInputStream fis = new FileInputStream(
            System.getProperty("user.dir") + "/src/test/resources/"+env+".properties")) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
