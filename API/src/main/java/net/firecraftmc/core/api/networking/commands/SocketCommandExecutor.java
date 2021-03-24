package net.firecraftmc.core.api.networking.commands;

public interface SocketCommandExecutor {
    void onCommand(SocketCommand socketCommand, String sender, String[] args);
}
