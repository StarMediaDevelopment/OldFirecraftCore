package net.firecraftmc.core.api.universe;

/*
Represents a server, this is the actual server representation for each of the connected servers
 */
public record FirecraftMinecraftServer(int id, String name, int onlinePlayers, int maxPlayers) {
}
