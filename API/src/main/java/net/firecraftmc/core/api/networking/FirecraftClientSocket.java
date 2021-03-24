package net.firecraftmc.core.api.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

//This represents a socket on a bungee proxy or a spigot server connected to the server socket
public class FirecraftClientSocket extends FirecraftSocket {
    
    public static final long HEARTBEAT = TimeUnit.SECONDS.toMillis(1);
    private final String host;
    private final int port;

    private long lastHeartbeat;
    
    public FirecraftClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void connect() {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {}
    }
    
    public void run() {
        while (isActive()) {
            
        }
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }
}
