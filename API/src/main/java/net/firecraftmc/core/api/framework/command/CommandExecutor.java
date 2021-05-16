package net.firecraftmc.core.api.framework.command;

public interface CommandExecutor {
    void onCommand(CommandSender sender, Command cmd, String[] args);
}
