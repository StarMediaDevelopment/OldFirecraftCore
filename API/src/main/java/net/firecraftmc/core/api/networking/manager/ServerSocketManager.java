package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftServerSocket;
import net.firecraftmc.core.api.networking.config.SocketConfig;

public class ServerSocketManager extends SocketManager {
    
    private FirecraftServerSocket socket;
    
    public void init(SocketConfig config) {
        socket = new FirecraftServerSocket(config.getHost(), config.getPort());
        socket.start();
    }
}
