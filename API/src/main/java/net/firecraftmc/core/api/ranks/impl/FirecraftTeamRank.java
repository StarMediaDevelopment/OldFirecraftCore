package net.firecraftmc.core.api.ranks.impl;

import net.firecraftmc.core.api.ranks.Rank;

public class FirecraftTeamRank extends Rank {
    public FirecraftTeamRank() {
        super("Firecraft Team", "&4", "&6", true, 5, 5, 0, 1000000);
    }

    public String getPrefix() {
        return "&4&lFIRECRAFT";
    }
}
