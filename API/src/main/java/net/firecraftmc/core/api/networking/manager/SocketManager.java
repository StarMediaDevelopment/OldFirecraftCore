package net.firecraftmc.core.api.networking.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SocketManager {
    
    protected ExecutorService executor = Executors.newFixedThreadPool(8);
    
    public abstract void init(String host, int port);

    public ExecutorService getExecutor() {
        return executor;
    }
}
