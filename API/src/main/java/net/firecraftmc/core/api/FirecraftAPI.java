package net.firecraftmc.core.api;

import com.starmediadev.data.Context;
import com.starmediadev.data.StarData;
import com.starmediadev.data.manager.DatabaseManager;

import java.util.logging.Logger;

public class FirecraftAPI {
    
    private static StarData starData;
    private static DatabaseManager databaseManager;
    private static Logger logger;
    
    public static void init(Logger logger) {
        FirecraftAPI.logger = logger;
        starData = new StarData(Context.SINGLE, logger);
        databaseManager = starData.getDatabaseManager();
    }
}