package net.firecraftmc.core.api.networking;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

//This is a parent class for other handling
public abstract class FirecraftSocket extends Thread {
    
    protected Socket socket;
    protected AtomicBoolean active;

    public boolean isActive() {
        return active.get();
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
