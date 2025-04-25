package backend.system;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".cypherfy";
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "config.properties";

    private static final Properties properties = new Properties();

    public ConfigManager() {
        ensureConfigExists();
        loadProperties();
    }

    private void ensureConfigExists() {
        try {
            Files.createDirectories(Paths.get(CONFIG_DIR));
            File configFile = new File(CONFIG_FILE);
            if (!configFile.exists()) {
                try (OutputStream out = new FileOutputStream(configFile)) {
                    // Write default settings
                    properties.setProperty("theme", "theme-default.css");
                    properties.store(out, "Default config");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to create default config: " + e.getMessage());
        }
    }

    private void loadProperties() {
        try (InputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            System.err.println("Failed to load config: " + e.getMessage());
        }
    }

    public static void setTheme(String theme) {
        properties.setProperty("theme", theme);
        try (OutputStream out = new FileOutputStream(CONFIG_FILE)) {
            properties.store(out, "Updated config");
        } catch (IOException e) {
            System.err.println("⚠️ Failed to save updated config: " + e.getMessage());
        }
    }


    public static String getTheme() {
        System.out.println(properties.getProperty("theme"));
        return properties.getProperty("theme");
    }
}
