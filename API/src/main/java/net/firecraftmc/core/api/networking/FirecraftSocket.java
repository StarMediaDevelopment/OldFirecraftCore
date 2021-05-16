package net.firecraftmc.core.api.networking;

import net.firecraftmc.core.api.FirecraftAPI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

//This is a parent class for other handling
public abstract class FirecraftSocket extends Thread {
    
    protected Socket socket;
    protected final AtomicBoolean active = new AtomicBoolean();
    protected String socketName;
    protected long lastHeartbeat = 0;

    public boolean isActive() {
        return active.get();
    }

    public String getSocketName() {
        return socketName;
    }
    
    public void sendCommand(String command) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                active.set(false);
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

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }
}
