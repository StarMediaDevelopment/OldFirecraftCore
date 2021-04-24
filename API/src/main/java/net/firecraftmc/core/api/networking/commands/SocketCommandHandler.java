package net.firecraftmc.core.api.networking.commands;

import net.firecraftmc.core.api.FirecraftAPI;
import net.firecraftmc.core.api.networking.FirecraftSocket;
import net.firecraftmc.core.api.networking.manager.SocketManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketCommandHandler {
    private List<SocketCommand> commands = Collections.synchronizedList(new ArrayList<>());

    public SocketCommandHandler() {
        addCommand(new SocketCommand("heartbeat", null, "Command to periodically check if a socket connection is active.").setExecutor((cmd, sender, args) -> {
            SocketManager socketManager = FirecraftAPI.getSocketManager();
            FirecraftSocket socket = socketManager.getSocket(sender);
            socket.setLastHeartbeat(System.currentTimeMillis());
            FirecraftAPI.getLogger().info("Socket Heartbeat " + sender);
        }));
    }

    public SocketCommand getCommand(String name) {
        for (SocketCommand cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            } else {
                if (cmd.getAliases() != null) {
                    for (String alias : cmd.getAliases()) {
                        if (alias.equalsIgnoreCase(name)) {
                            return cmd;
                        }
                    }
                }
            }
        }
        return null;
    }

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

    public void handleCommandInput(String message) {
        FirecraftAPI.getLogger().info("Handling command input " + message);
        String[] messageSplit = message.split(" ");
        String server;
        String[] args;
        if (messageSplit.length < 3)
            return;
        SocketCommand command = getCommand(messageSplit[0]);
        server = messageSplit[1];
        args = new String[messageSplit.length - 2];
        System.arraycopy(messageSplit, 2, args, 0, args.length);
        if (command == null)
            return;
        if (command.getExecutor() != null) {
            command.getExecutor().onCommand(command, server, args);
        }
    }
}
