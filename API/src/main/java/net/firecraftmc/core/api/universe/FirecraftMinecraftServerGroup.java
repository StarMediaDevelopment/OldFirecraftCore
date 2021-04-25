package net.firecraftmc.core.api.universe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
Represents a group of minecraft servers for different purposes, used with cloud based things
 */
public class FirecraftMinecraftServerGroup {
    private int id;
    private UUID uuid;
    private String name;
    private ServerType type;
    private Set<FirecraftMinecraftServer> servers = new HashSet<>();
}