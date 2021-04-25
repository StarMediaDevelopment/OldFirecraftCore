package net.firecraftmc.core.bungee;

import net.firecraftmc.core.api.FirecraftAPI;
import net.firecraftmc.core.api.module.FirecraftModule;
import net.md_5.bungee.api.plugin.Plugin;

import java.nio.file.Paths;

public class FirecraftCoreBungee extends Plugin implements FirecraftModule {

    public void onLoad() {
        FirecraftAPI.load(getLogger(), Paths.get("./"));
        FirecraftAPI.setModule(this);
    }
}