package net.firecraftmc.core.api;

import com.moandjiezana.toml.Toml;
import com.starmediadev.data.Context;
import com.starmediadev.data.StarData;
import com.starmediadev.data.manager.DatabaseManager;
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
    
    private static File configFile;

    public static void init(Logger logger, File confFile) {
        FirecraftAPI.configFile = confFile;
        
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {}
            
            //TODO default options
        }

        Toml toml = new Toml().read(configFile);
        String rawContext = toml.getString("socket.context");
        SocketContext context = null;
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
        

        FirecraftAPI.logger = logger;
        starData = new StarData(Context.SINGLE, logger);
        databaseManager = starData.getDatabaseManager();
//        if (context == SocketContext.SERVER) {
//            socketManager = new ServerSocketManager();
//        } else if (context == SocketContext.CLIENT) {
//            socketManager = new ClientSocketManager();
//        }

        socketCommandHandler = new SocketCommandHandler();
        databaseManager.setup();
    }

    public static SocketCommandHandler getSocketCommandHandler() {
        return socketCommandHandler;
    }

    public static SocketManager getSocketManager() {
        return socketManager;
    }
    
    public static File getConfigurationFile() {
        return configFile;
    }
}