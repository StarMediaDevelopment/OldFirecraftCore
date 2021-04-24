package net.firecraftmc.core.api.server;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FirecraftNetwork {
    private int id;
    private UUID uuid;
    private String name;
    private Set<FirecraftServerGroup> groups = new HashSet<>();
    private Set<FirecraftServer> servers = new HashSet<>();
}
