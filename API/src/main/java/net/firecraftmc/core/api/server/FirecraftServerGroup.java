package net.firecraftmc.core.api.server;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FirecraftServerGroup {
    private int id;
    private UUID uuid;
    private String name;
    private ServerType type;
    private Set<FirecraftServer> servers = new HashSet<>();
}