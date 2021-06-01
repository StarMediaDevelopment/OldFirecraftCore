package net.firecraftmc.core.api.universe;

/*
Represents a group of similar minecraft servers like minigames or the island servers for Iron Havens
 */
public record FirecraftMinecraftServerGroup(int id, String name, int totalServers, int onlineServers) {
    
}