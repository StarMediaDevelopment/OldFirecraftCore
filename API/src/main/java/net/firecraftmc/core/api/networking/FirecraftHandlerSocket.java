package net.firecraftmc.core.api.networking;

import net.firecraftmc.core.api.networking.packets.FirecraftHeartbeatPacket;
import net.firecraftmc.core.api.networking.packets.FirecraftPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.*;

//This is a handler class for handling server socket connections on the server side
public class FirecraftHandlerSocket extends FirecraftSocket {

    private final FirecraftServerSocket serverSocket;
    private long lastHeartbeat = 0;

    private List<PacketListener> packetListeners = Collections.synchronizedList(new ArrayList<>());
    private Map<Class<? extends PacketListener>, List<Method>> listenerMethods = Collections.synchronizedMap(new HashMap<>());

    public FirecraftHandlerSocket(Socket socket, FirecraftServerSocket serverSocket) {
        this.socket = socket;
        this.serverSocket = serverSocket;
    }

    public void run() {
        while (isActive()) {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                Object object = in.readObject();
                if (!(object instanceof FirecraftPacket))
                    continue;
                FirecraftPacket packet = (FirecraftPacket) object;
                if (packet instanceof FirecraftHeartbeatPacket) {
                    FirecraftHeartbeatPacket heartbeatPacket = (FirecraftHeartbeatPacket) packet;
                    this.lastHeartbeat = heartbeatPacket.getTime();
                    continue;
                }

                new Thread(() -> {
                    for (PacketListener packetListener : packetListeners) {
                        List<Method> methods = this.listenerMethods.get(packetListener.getClass());
                        for (Method method : methods) {
                            if (packet.getClass().isAssignableFrom(method.getParameters()[0].getType())) {
                                try {
                                    method.invoke(packetListener, packet);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPacketListener(PacketListener listener) {
        List<Method> methods = new ArrayList<>();
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PacketHandler.class)) {
                if (method.getParameters().length == 1) {
                    Parameter parameter = method.getParameters()[0];
                    if (FirecraftPacket.class.isAssignableFrom(parameter.getType())) {
                        if (FirecraftHeartbeatPacket.class.isAssignableFrom(parameter.getType())) {
                            continue;
                        }
                        methods.add(method);
                    }
                }
            }
        }

        this.listenerMethods.put(listener.getClass(), methods);
        this.packetListeners.add(listener);
    }

    public long getLastHeartbeat() {
        return lastHeartbeat;
    }
}
