package net.firecraftmc.core.api.networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//This represents a server socket that is running to receive connections from other servers, there should be only one running
public class FirecraftServerSocket extends Thread {
    
    private ServerSocket serverSocket;
    
    private List<FirecraftHandlerSocket> handlers = Collections.synchronizedList(new ArrayList<>());
    public FirecraftServerSocket(String host, int port) {
        try {
            this.serverSocket = new ServerSocket();
            InetSocketAddress address = new InetSocketAddress(host, port);
            this.serverSocket.bind(address);
        } catch (IOException e) {}
    }

    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                FirecraftHandlerSocket handler = new FirecraftHandlerSocket(socket, this);
                this.handlers.add(handler);
                handler.start();
            } catch (IOException e) {}
        }
    }
    
    public boolean isConnected() {
        return false; //TODO
    }

    public List<FirecraftHandlerSocket> getHandlers() {
        return handlers;
    }
}