package net.firecraftmc.core.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starmediadev.data.Context;
import com.starmediadev.data.StarData;
import com.starmediadev.data.manager.DatabaseManager;
import com.starmediadev.data.properties.SqlProperties;
import com.starmediadev.networking.SocketContext;
import com.starmediadev.networking.StarNetworking;
import net.firecraftmc.core.api.module.FirecraftModule;
import net.firecraftmc.core.api.universe.FirecraftUniverse;
import net.firecraftmc.core.api.universe.UniverseContext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FirecraftAPI {

    private static StarData starData;
    private static DatabaseManager databaseManager;
    private static Logger logger;

    private static File storageFolder;
    private static Path storagePath;

    private static FirecraftUniverse universe;

    private static FirecraftModule module;

    public static boolean load(Logger logger, Path folder) {
        FirecraftAPI.logger = logger;
        logger.info("Loading FirecraftAPI");
        FirecraftAPI.storagePath = folder;
        logger.info("Determined storage folder to be " + folder.toAbsolutePath());
        if (Files.notExists(folder)) {
            logger.severe("Storage folder does not exist, creating it...");
            try {
                Files.createDirectory(folder);
            } catch (IOException e) {
                logger.severe("Could not create the storage folder " + e.getMessage());
                return false;
            }
            logger.info("Successfully created the storage folder.");
        }

        Path configFile = Paths.get(folder.toString(), "firecraftconfg.json");
        logger.info("Config file " + configFile.toAbsolutePath());
        if (Files.notExists(configFile)) {
            logger.severe("Config file does not exist, creating it");
            try {
                Files.createFile(configFile);
                String defaultConfig = new GsonBuilder().setPrettyPrinting().create().toJson(new Config());
                try (BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8)) {
                    writer.write(defaultConfig);
                    writer.flush();
                }
            } catch (Exception e) {
                logger.severe("Could not create the config file " + e.getMessage());
                return false;
            }
            logger.info("Successfully created the config file.");
        }

        Config config;
        try (BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8)) {
            config = new Gson().fromJson(reader, Config.class);
            System.out.println(config);
        } catch (Exception e) {
            logger.severe("Could not read the configuration file " + e.getMessage());
            return false;
        }

        if (config == null) {
            logger.severe("No settings loaded from the config file!");
            return false;
        }

        String rawContext = config.socket.context;
        SocketContext context;
        try {
            context = SocketContext.valueOf(rawContext.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.severe("Could not get a context from the config file.");
            return false;
        }

        StarNetworking.init(context, config.socket.host, Integer.parseInt(config.socket.port), logger);

        starData = new StarData(Context.SINGLE, logger);
        databaseManager = starData.getDatabaseManager();
        SqlProperties properties = new SqlProperties();
        properties.setHost(config.database.host);
        properties.setUsername(config.database.username);
        properties.setPassword(config.database.password);
        properties.setPort(Integer.parseInt(config.database.port));
        properties.setDatabase(config.database.database);

        databaseManager.createDatabase(properties);
        databaseManager.setup();
        logger.info("Initiated database connection and setup.");

        universe = new FirecraftUniverse(UniverseContext.valueOf(config.universe.context.toUpperCase()));
        logger.info("Setting up the FirecraftUniverse with the context " + universe.getContext().name());
        return true;
    }

    public static void enable() {
        module.onEnable();
    }

    public static void disable() {
        module.onDisable();
    }

    public static FirecraftUniverse getUniverse() {
        return universe;
    }

    public static File getStorageFolder() {
        return storageFolder;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setModule(FirecraftModule module) {
        FirecraftAPI.module = module;
    }

    static class DatabaseConfig {
        String host = "hostname";
        String username = "username";
        String database = "database";
        String password = "password";
        String port = "3306";
    }

    static class SocketConfig {
        String context = "UNKNOWN";
        String host = "localhost";
        String port = "1000";
    }

    static class UniverseConfig {
        String context = "UNKNOWN";
    }

    static class Config {
        SocketConfig socket = new SocketConfig();
        DatabaseConfig database = new DatabaseConfig();
        UniverseConfig universe = new UniverseConfig();
    }
}