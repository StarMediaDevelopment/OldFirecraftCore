package net.firecraftmc.core.api.networking.config;

import com.starmediadev.data.model.IRecord;

public class SocketConfig implements IRecord {
    
    protected int id;
    protected String host;
    protected int port;

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
