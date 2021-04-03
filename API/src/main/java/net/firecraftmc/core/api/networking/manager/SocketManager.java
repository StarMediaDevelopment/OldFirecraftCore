package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.FirecraftSocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SocketManager {
    
    protected ExecutorService executor = Executors.newFixedThreadPool(8);
    
    public abstract void init(String host, int port);
    
    public abstract FirecraftSocket getSocket(String name);

    public ExecutorService getExecutor() {
        return executor;
    }
}
