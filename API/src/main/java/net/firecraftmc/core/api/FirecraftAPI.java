package net.firecraftmc.core.api;

import com.starmediadev.data.Context;
import com.starmediadev.data.StarData;
import com.starmediadev.data.manager.DatabaseManager;
import net.firecraftmc.core.api.networking.SocketContext;
import net.firecraftmc.core.api.networking.commands.SocketCommandHandler;
import net.firecraftmc.core.api.networking.manager.ClientSocketManager;
import net.firecraftmc.core.api.networking.manager.ServerSocketManager;
import net.firecraftmc.core.api.networking.manager.SocketManager;

import java.util.logging.Logger;

public class FirecraftAPI {

    private static StarData starData;
    private static DatabaseManager databaseManager;
    private static Logger logger;
    private static SocketManager socketManager;
    private static SocketCommandHandler socketCommandHandler;

    public static void init(Logger logger, SocketContext context) {
        FirecraftAPI.logger = logger;
        starData = new StarData(Context.SINGLE, logger);
        databaseManager = starData.getDatabaseManager();
        if (context == SocketContext.SERVER) {
            socketManager = new ServerSocketManager();
        } else if (context == SocketContext.CLIENT) {
            socketManager = new ClientSocketManager();
        }

        socketCommandHandler = new SocketCommandHandler();
        databaseManager.setup();
    }

    public static SocketCommandHandler getSocketCommandHandler() {
        return socketCommandHandler;
    }

    public static SocketManager getSocketManager() {
        return socketManager;
    }
}