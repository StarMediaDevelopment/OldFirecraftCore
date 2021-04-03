package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftClientSocket;
import net.firecraftmc.core.api.networking.FirecraftSocket;

public class ClientSocketManager extends SocketManager {
    
    private FirecraftClientSocket socket;
    
    public void init(String host, int port) {
        this.socket = new FirecraftClientSocket(host, port);
        this.socket.start();
    }

    public FirecraftSocket getSocket(String name) {
        return socket;
    }
}
