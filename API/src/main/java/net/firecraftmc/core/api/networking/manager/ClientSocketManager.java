package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftClientSocket;
import net.firecraftmc.core.api.networking.config.SocketConfig;

public class ClientSocketManager extends SocketManager {
    
    private FirecraftClientSocket socket;
    
    public void init(SocketConfig config) {
        this.socket = new FirecraftClientSocket(config.getHost(), config.getPort());
    }
}
