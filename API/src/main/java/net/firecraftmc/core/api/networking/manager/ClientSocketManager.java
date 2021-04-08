package net.firecraftmc.core.api.networking.manager;

import com.starmediadev.utils.Utils;
import net.firecraftmc.core.api.FirecraftAPI;
import net.firecraftmc.core.api.networking.FirecraftClientSocket;
import net.firecraftmc.core.api.networking.FirecraftSocket;
import net.firecraftmc.core.api.networking.commands.SocketCommand;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ClientSocketManager extends SocketManager {
    
    private FirecraftClientSocket socket;
    
    public void init(String host, int port) {
        this.socket = new FirecraftClientSocket(host, port);
        this.socket.setDaemon(true);
        this.socket.start();
        
        new Thread(() -> {
            while (socket.isActive()) {
                SocketCommand heartbeatCmd = FirecraftAPI.getSocketCommandHandler().getCommand("heartbeat");
                sendSocketCommand(heartbeatCmd, "test", null);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public FirecraftSocket getSocket(String name) {
        return socket;
    }

    public void sendSocketCommand(SocketCommand cmd, String sender, String[] args) {
        socket.sendCommand(cmd.getName() + " " + sender + " " + Utils.join(Arrays.asList(args), " "));
    }
}
