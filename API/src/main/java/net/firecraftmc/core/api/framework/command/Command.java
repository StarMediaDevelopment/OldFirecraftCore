package net.firecraftmc.core.api.framework.command;

public class Command {
    private final String name;
    private final String[] aliases;
    private final String description;

    private CommandExecutor executor;

    public Command(String name, String[] aliases, String description) {
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

    public CommandExecutor getExecutor() {
        return executor;
    }

    public Command setExecutor(CommandExecutor executor) {
        this.executor = executor;
        return this;
    }
}
