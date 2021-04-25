package net.firecraftmc.core.api.universe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
Represents a minecraft network, the current three networks are IronHavens, Isorith and Centurions Battlegrounds
 */
public class FirecraftMinecraftNetwork {
    private int id;
    private UUID uuid;
    private String name;
    private Set<FirecraftMinecraftServerGroup> groups = new HashSet<>();
    private Set<FirecraftMinecraftServer> servers = new HashSet<>();
}
