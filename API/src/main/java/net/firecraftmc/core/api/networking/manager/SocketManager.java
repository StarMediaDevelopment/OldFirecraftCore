package net.firecraftmc.core.api.networking.manager;

import net.firecraftmc.core.api.networking.config.SocketConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class SocketManager {
    
    protected ExecutorService executor = Executors.newFixedThreadPool(8);
    
    public abstract void init(SocketConfig config);

    public ExecutorService getExecutor() {
        return executor;
    }
}