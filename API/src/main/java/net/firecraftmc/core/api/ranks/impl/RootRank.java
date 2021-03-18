package net.firecraftmc.core.api.ranks.impl;

import net.firecraftmc.core.api.ranks.Rank;

public class RootRank extends Rank {
    public RootRank() {
        super("Root", "&4", "&6", true, 5, 5, 0, Integer.MAX_VALUE);
    }

    public String getPrefix() {
        return "&4&lFIRECRAFT";
    }
}
