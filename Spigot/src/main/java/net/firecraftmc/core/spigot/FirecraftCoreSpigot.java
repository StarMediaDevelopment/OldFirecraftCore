package net.firecraftmc.core.spigot;

import net.firecraftmc.core.api.FirecraftAPI;
import net.firecraftmc.core.api.module.FirecraftModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Paths;

public class FirecraftCoreSpigot extends JavaPlugin implements FirecraftModule {

    public void onLoad() {
        FirecraftAPI.load(getLogger(), Paths.get("./"));
        FirecraftAPI.setModule(this);
    }
}