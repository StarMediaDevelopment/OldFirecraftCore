package net.firecraftmc.core.api.ranks.impl;

import net.firecraftmc.core.api.ranks.Rank;

public class AdminRank extends Rank {
    public AdminRank() {
        super("Admin", "#ff0000", "&f", true, 4, 4, 0, 10000);
    }
}
