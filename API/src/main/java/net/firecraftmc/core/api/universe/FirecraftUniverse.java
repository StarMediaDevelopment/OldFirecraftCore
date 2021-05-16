package net.firecraftmc.core.api.universe;

import java.util.HashSet;
import java.util.Set;

/*
Represents the whole FirecraftMC Universe. This is per instance that this is running so all information is provided
These are meant as representations of each and changes to the info doesn't actually change anything. 
 */
public final class FirecraftUniverse {
    private final UniverseContext context;
    private Set<FirecraftMinecraftNetwork> networks = new HashSet<>();
    private Set<FirecraftMinecraftServerGroup> groups = new HashSet<>();
    private Set<FirecraftMinecraftServer> servers = new HashSet<>();
    private Set<FirecraftMinecraftCloud> clouds = new HashSet<>();
    private Set<FirecraftDiscordServer> discordServers = new HashSet<>();

    public FirecraftUniverse(UniverseContext context) {
        this.context = context;
    }

    public UniverseContext getContext() {
        return context;
    }
}
