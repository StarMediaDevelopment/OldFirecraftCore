package net.firecraftmc.core.api;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import com.starmediadev.data.Context;
import com.starmediadev.data.StarData;
import com.starmediadev.data.manager.DatabaseManager;
import com.starmediadev.data.properties.SqlProperties;
import net.firecraftmc.core.api.networking.SocketContext;
import net.firecraftmc.core.api.networking.commands.SocketCommandHandler;
import net.firecraftmc.core.api.networking.manager.ClientSocketManager;
import net.firecraftmc.core.api.networking.manager.ServerSocketManager;
import net.firecraftmc.core.api.networking.manager.SocketManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FirecraftAPI {

    private static StarData starData;
    private static DatabaseManager databaseManager;
    private static Logger logger;
    private static SocketManager socketManager;
    private static SocketCommandHandler socketCommandHandler;
    
    private static File storageFolder;
    
    public static void main(String[] args) {
        init(StarData.createLogger(FirecraftAPI.class), new File("./"));
    }

    public static void init(Logger logger, File folder) {
        logger.info("Loading FirecraftAPI");
        FirecraftAPI.storageFolder = folder;
        
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        File configFile = new File(storageFolder + File.separator + "firecraftconfig.toml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();

                TomlWriter writer = new TomlWriter.Builder().indentValuesBy(2).indentTablesBy(4).padArrayDelimitersBy(3).build();

                Config config = new Config();
                try {
                    writer.write(config, configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.warning("Generated a new config file as one did not exist. Please configure and try again.");
            return;
        }

        Toml toml = new Toml().read(configFile);

        String rawContext = toml.getString("socket.context");
        SocketContext context;
        try {
            context = SocketContext.valueOf(rawContext.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.severe("Could not get a context from the config file.");
            return;
        }
        
        if (context == SocketContext.SERVER) {
            socketManager = new ServerSocketManager();
        } else if (context == SocketContext.CLIENT) {
            socketManager = new ClientSocketManager();
        } else {
            logger.severe("Could not find a valid socket context");
            return;
        }
        
        socketCommandHandler = new SocketCommandHandler();
        socketManager.init(toml.getString("socket.host"), Integer.parseInt(toml.getString("socket.port")));
        logger.info("Initiated a socket with the context " + context.name());

        FirecraftAPI.logger = logger;
        starData = new StarData(Context.SINGLE, logger);
        databaseManager = starData.getDatabaseManager();
        SqlProperties properties = new SqlProperties();
        properties.setHost(toml.getString("database.host"));
        properties.setUsername(toml.getString("database.username"));
        properties.setPassword(toml.getString("database.password"));
        properties.setPort(Integer.parseInt(toml.getString("database.port")));
        properties.setDatabase(toml.getString("database.database"));
        
        databaseManager.createDatabase(properties);
        databaseManager.setup();
        logger.info("Initiated database connection and setup.");
    }

    public static SocketCommandHandler getSocketCommandHandler() {
        return socketCommandHandler;
    }

    public static SocketManager getSocketManager() {
        return socketManager;
    }
    
    public static File getStorageFolder() {
        return storageFolder;
    }

    public static Logger getLogger() {
        return logger;
    }

    static class DatabaseConfig {
        String host = "hostname";
        String username = "username";
        String database = "database", password = "password", port = "3306";
    }

    static class SocketConfig {
        String context = "UNKNOWN", host = "localhost", port = "1000";
    }

    static class Config {
        SocketConfig socket = new SocketConfig();
        DatabaseConfig database = new DatabaseConfig();
    }
}