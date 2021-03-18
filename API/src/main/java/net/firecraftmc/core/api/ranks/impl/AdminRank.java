package net.firecraftmc.core.api.ranks.impl;

import net.firecraftmc.core.api.ranks.Rank;

public class AdminRank extends Rank {
    public AdminRank() {
        super("Admin", "&9", "&f", true, 4, 4, 0, 10000);
    }
}
