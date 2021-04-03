package net.firecraftmc.core.api.networking;

import net.firecraftmc.core.api.FirecraftAPI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

//This is a parent class for other handling
public abstract class FirecraftSocket extends Thread {
    
    protected Socket socket;
    protected AtomicBoolean active;

    public boolean isActive() {
        return active.get();
    }

    public void run() {
        while (isActive()) {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Object object = in.readObject();
                if (object instanceof String) {
                    new Thread(() -> FirecraftAPI.getSocketCommandHandler().handleCommandInput((String) object)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
