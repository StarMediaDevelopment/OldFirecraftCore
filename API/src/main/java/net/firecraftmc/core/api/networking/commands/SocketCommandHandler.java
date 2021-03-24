package net.firecraftmc.core.api.networking.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketCommandHandler {
    private List<SocketCommandExecutor> executors = Collections.synchronizedList(new ArrayList<>());
    private List<SocketCommand> commands = Collections.synchronizedList(new ArrayList<>());
    
    public void addCommand(Object object) {
        if (object instanceof SocketCommand) {
            commands.add((SocketCommand) object);
        } else if (object.getClass().isAnnotationPresent(SocketCmd.class)) {
            SocketCmd cmdInfo = object.getClass().getAnnotation(SocketCmd.class);
            if (cmdInfo != null) {
                SocketCommand socketCommand = new SocketCommand(cmdInfo.name(), cmdInfo.aliases(), cmdInfo.description());
                commands.add(socketCommand);
            }
        }
    }
    
    public void addCommandExecutor(SocketCommandExecutor executor) {
        this.executors.add(executor);
    }

    public void handleCommandInput(String message) {
        String[] messageSplit = message.split(" ");
        SocketCommand command = null;
        String server;
        String[] args;
        if (messageSplit.length < 3)
            return;
        cmdLoop:
        for (SocketCommand cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(messageSplit[0])) {
                command = cmd;
                break;
            } else {
                if (cmd.getAliases() != null) {
                    for (String alias : cmd.getAliases()) {
                        if (alias.equalsIgnoreCase(messageSplit[0])) {
                            command = cmd;
                            break cmdLoop;
                        }
                    }
                }
            }
        }
        server = messageSplit[1];
        args = new String[messageSplit.length - 2];
        System.arraycopy(messageSplit, 2, args, 0, args.length);
        if (command == null) return;
        for (SocketCommandExecutor executor : executors) {
            executor.onCommand(command, server, args);
        }
    }
}
