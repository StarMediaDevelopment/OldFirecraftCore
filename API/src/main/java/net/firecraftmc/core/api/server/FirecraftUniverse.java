package net.firecraftmc.core.api.server;

import java.util.HashSet;
import java.util.Set;

public class FirecraftUniverse {
    private Set<FirecraftNetwork> networks = new HashSet<>();
    private Set<FirecraftServerGroup> groups = new HashSet<>();
    private Set<FirecraftServer> servers = new HashSet<>();
}
