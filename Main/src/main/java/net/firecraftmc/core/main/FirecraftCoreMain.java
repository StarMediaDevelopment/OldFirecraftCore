package net.firecraftmc.core.main;

import com.starmediadev.data.StarData;
import net.firecraftmc.core.api.FirecraftAPI;
import net.firecraftmc.core.cloud.FirecraftCoreCloud;
import net.firecraftmc.core.discord.FirecraftCoreDiscord;
import net.firecraftmc.core.socket.FirecraftCoreSocket;

import java.nio.file.Paths;

public class FirecraftCoreMain {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        FirecraftAPI.load(StarData.createLogger(FirecraftAPI.class), Paths.get("./"));
        switch (FirecraftAPI.getUniverse().getContext()) {
            case SOCKET: FirecraftAPI.setModule(new FirecraftCoreSocket());
                break;
            case BUNGEE:
            case SPIGOT:
            case UNKNOWN:
                break;
            case CLOUD: FirecraftAPI.setModule(new FirecraftCoreCloud());
                break;
            case DISCORD: FirecraftAPI.setModule(new FirecraftCoreDiscord());
                break;
        }
        
        FirecraftAPI.enable();
    }
    
    public static void shutdown() {
        //Add a shutdown hook
        FirecraftAPI.disable();
    }
}