package net.firecraftmc.core.api.universe;

import java.util.UUID;

/*
Represents an actual minecraft server and the information provided
 */
public class FirecraftMinecraftServer {
    private int id;
    private String name;
    private UUID uniqueId;
    private ServerType type;
}