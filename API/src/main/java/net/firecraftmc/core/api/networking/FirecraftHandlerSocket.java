package net.firecraftmc.core.api.networking;

import java.net.Socket;

//This is a handler class for handling server socket connections on the server side
public class FirecraftHandlerSocket extends FirecraftSocket {
    
    public FirecraftHandlerSocket(Socket socket) {
        this.socket = socket;
    }
}
