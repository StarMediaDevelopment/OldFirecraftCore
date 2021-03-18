package net.firecraftmc.core.api.ranks;

import net.firecraftmc.core.api.ranks.impl.*;

public class Ranks {
    public static final Rank ROOT = new RootRank();
    public static final Rank FIRECRAFT_TEAM = new FirecraftTeamRank();
    public static final Rank CONSOLE = new ConsoleRank();
    public static final Rank DEVELOPER = new DeveloperRank();
    public static final Rank BUILDER = new BuilderRank();
    public static final Rank ADMIN = new AdminRank();
    public static final Rank SR_MOD = new SrModRank();
    public static final Rank MOD = new ModRank();
    public static final Rank HELPER = new HelperRank();
}