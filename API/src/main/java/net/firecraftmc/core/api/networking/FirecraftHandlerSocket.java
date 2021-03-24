package net.firecraftmc.core.api.networking;

import net.firecraftmc.core.api.FirecraftAPI;

import java.io.ObjectInputStream;
import java.net.Socket;

//This is a handler class for handling server socket connections on the server side
public class FirecraftHandlerSocket extends FirecraftSocket {

    private final FirecraftServerSocket serverSocket;
    private long lastHeartbeat = 0;

    public FirecraftHandlerSocket(Socket socket, FirecraftServerSocket serverSocket) {
        this.socket = socket;
        this.serverSocket = serverSocket;
    }

    public void run() {
        while (isActive()) {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Object object = in.readObject();
                if (object instanceof String) {
                    new Thread(() -> FirecraftAPI.getSocketCommandHandler().handleCommandInput((String) object)).start();
                }
            } catch (Exception e) {}
        }
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }
}
