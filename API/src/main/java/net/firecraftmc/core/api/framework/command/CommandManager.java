package net.firecraftmc.core.api.framework.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final String starting_char;
    public List<Command> commands = new ArrayList<>();

    public CommandManager(String starting_char) {
        this.starting_char = starting_char;
    }

    public void handleInput(CommandSender sender, String input) {
        String[] split = input.split(" ");
        if (!(split.length > 0)) {
            return;
        }
        String cmdName = split[0];
        if (cmdName.startsWith(starting_char)) {
            cmdName = cmdName.substring(1);
        } else {
            return;
        }

        Command command = getCommand(cmdName);
        if (command == null) {
            return;
        }
        if (command.getExecutor() == null) {
            return;
        }
        String[] args;
        if (split.length > 1) {
            args = new String[split.length - 1];
            System.arraycopy(split, 1, args, 0, split.length - 1);
        } else {
            args = new String[0];
        }
        command.getExecutor().onCommand(sender, command, args);
    }

    public void registerCommand(Command command) {
        if (getCommand(command.getName()) == null) {
            this.commands.add(command);
        }
    }

    public Command getCommand(String name) {
        for (Command command : this.commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }
}
