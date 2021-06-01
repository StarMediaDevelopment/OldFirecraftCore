package net.firecraftmc.core.api.ranks;

public class Ranks {
    public static final Rank FIRECRAFT_TEAM = new Rank("Firecraft Team", "#AA0000", Integer.MAX_VALUE);
    public static final Rank ADMIN = new Rank("Admin", "#ff0000", 43);
    public static final Rank SR_MOD = new Rank("Sr Mod", "#11806a", 42);
    public static final Rank MOD = new Rank("Mod", "#00aaaa", 41);
    public static final Rank HELPER = new Rank("Helper", "#ff55ff", 40);

    public static final Rank DEVELOPER = new Rank("Developer", "#3c40f0", 31);
    public static final Rank BUILDER = new Rank("Builder", "#a39b49", 30);
    
    public static final Rank VIP = new Rank("VIP", "", 22);
    public static final Rank MEDIA = new Rank("Media", "", 21);
    public static final Rank TRUSTED = new Rank("Trusted", "", 20);

    public static final Rank ROYAL = new Rank("Royal", "", 14);
    public static final Rank MARSHAL = new Rank("Marshal", "", 13);
    public static final Rank GENERAL = new Rank("General", "", 12);
    public static final Rank WARDEN = new Rank("Warden", "", 11);
    public static final Rank MERCENARY = new Rank("Mercenary", "", 10);
    
    public static final Rank MEMBER = new Rank("Member", "", 1);
}