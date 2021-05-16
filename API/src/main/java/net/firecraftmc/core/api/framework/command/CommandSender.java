package net.firecraftmc.core.api.framework.command;

public interface CommandSender {
    void sendMessage(String message);
    String getName();
}
