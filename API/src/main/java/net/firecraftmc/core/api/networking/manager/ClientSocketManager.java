package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftClientSocket;
import net.firecraftmc.core.api.networking.FirecraftSocket;

import java.util.concurrent.TimeUnit;

public class ClientSocketManager extends SocketManager {
    
    private FirecraftClientSocket socket;
    
    public void init(String host, int port) {
        this.socket = new FirecraftClientSocket(host, port);
        this.socket.setDaemon(true);
        this.socket.start();
        
        new Thread(() -> {
            socket.sendCommand("heartbeat");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public FirecraftSocket getSocket(String name) {
        return socket;
    }
}
