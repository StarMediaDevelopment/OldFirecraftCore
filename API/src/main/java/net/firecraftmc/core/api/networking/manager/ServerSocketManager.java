package net.firecraftmc.core.api.networking.manager;

import com.starmediadev.utils.Utils;
import net.firecraftmc.core.api.networking.FirecraftHandlerSocket;
import net.firecraftmc.core.api.networking.FirecraftServerSocket;
import net.firecraftmc.core.api.networking.FirecraftSocket;
import net.firecraftmc.core.api.networking.commands.SocketCommand;

import java.util.Arrays;

public class ServerSocketManager extends SocketManager {
    
    private FirecraftServerSocket socket;
    
    public void init(String host, int port) {
        socket = new FirecraftServerSocket(host, port);
        socket.start();
    }

    public FirecraftSocket getSocket(String name) {
        for (FirecraftHandlerSocket handler : socket.getHandlers()) {
            if (handler.getSocketName().equalsIgnoreCase(name)) {
                return handler;
            }
        }
        return null;
    }

    public void sendSocketCommand(SocketCommand cmd, String sender, String[] args) {
        socket.sendCommand(cmd.getName() + " " + sender + " " + Utils.join(Arrays.asList(args), " "));
    }
}
