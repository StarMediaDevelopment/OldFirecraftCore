package net.firecraftmc.core.api.networking.commands;

public class SocketCommand {
    private String name;
    private String[] aliases;
    private String description;

    public SocketCommand(String name, String[] aliases, String description) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }
}
