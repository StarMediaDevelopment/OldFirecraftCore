package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftServerSocket;

public class ServerSocketManager extends SocketManager {
    
    private FirecraftServerSocket socket;
    
    public void init(String host, int port) {
        socket = new FirecraftServerSocket(host, port);
        socket.start();
    }
}
