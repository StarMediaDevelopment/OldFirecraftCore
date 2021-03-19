package net.firecraftmc.core.api.networking.packets;

public class FirecraftHeartbeatPacket extends FirecraftPacket {
    
    private long time;

    public FirecraftHeartbeatPacket(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
