package net.firecraftmc.core.api;

import com.moandjiezana.toml.Toml;
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
import java.util.logging.Logger;

public class FirecraftAPI {

    private static StarData starData;
    private static DatabaseManager databaseManager;
    private static Logger logger;
    private static SocketManager socketManager;
    private static SocketCommandHandler socketCommandHandler;
    
    private static File storageFolder;

    public static void init(Logger logger, File folder) {
        FirecraftAPI.storageFolder = folder;
        
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        Toml toml = new Toml().read(new File(storageFolder + File.separator + "firecraftconfig.toml"));
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
        }
        
        socketCommandHandler = new SocketCommandHandler();
        socketManager.init(toml.getString("socket.host"), Integer.parseInt(toml.getString("socket.port")));

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
}