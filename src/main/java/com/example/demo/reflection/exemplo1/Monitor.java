package com.example.demo.reflection.exemplo1;

import lombok.Data;

import java.io.Serializable;

@Data
public class Monitor implements Serializable {

    private String name;

    private Integer serverId;

    private Integer enabled;

    private String linkedMonitors;

    private String device;

    private Integer channel;

    private Integer format;

    @Override
    public String toString() {
        return "Monitor{" +
                "name='" + name + '\'' +
                ", serverId=" + serverId +
                ", enabled=" + enabled +
                ", linkedMonitors='" + linkedMonitors + '\'' +
                ", device='" + device + '\'' +
                ", channel=" + channel +
                ", format=" + format +
                '}';
    }
}
