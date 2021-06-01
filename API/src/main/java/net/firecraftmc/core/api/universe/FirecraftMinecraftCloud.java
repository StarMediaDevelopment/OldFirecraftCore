package net.firecraftmc.core.api.universe;

import java.util.List;

/*
Represents a minecraft cloud instance on the network, mostly used for information regarding the status of the cloud(s)
 */
public record FirecraftMinecraftCloud(int id, String name, int totalServers, String status, List<String> servers) {
}
