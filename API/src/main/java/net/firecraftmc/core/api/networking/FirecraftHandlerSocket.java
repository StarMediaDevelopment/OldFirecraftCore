package net.firecraftmc.core.api.networking;

import java.net.Socket;

//This is a handler class for handling server socket connections on the server side
public class FirecraftHandlerSocket extends FirecraftSocket {

    private final FirecraftServerSocket serverSocket;

    public FirecraftHandlerSocket(Socket socket, FirecraftServerSocket serverSocket) {
        this.socket = socket;
        this.serverSocket = serverSocket;
    }
}
