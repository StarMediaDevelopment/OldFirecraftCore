package net.firecraftmc.core.api.universe;

import java.util.List;

/*
Represents a discord server, this is controlled by a single bot running in a different program to ensure stability.
 */
public record FirecraftDiscordServer(int guildId, List<String> onlineMembers, List<String> offlineMembers) {}