package com.example.demo.reflection.exemplo1.impl;

import com.example.demo.reflection.exemplo1.Monitor;
import com.example.demo.reflection.exemplo1.PresetMonitorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PresetMonitorImpl implements PresetMonitorConfiguration {

    @Bean
    @Override
    public Map<String, Object> configuration() {
        Map<String, Object> preset = new HashMap<>();

        preset.put("serverId", getPresetMonitor().getServerId());
        preset.put("name", getPresetMonitor().getName());
        preset.put("enabled", getPresetMonitor().getEnabled());
        preset.put("channel", getPresetMonitor().getChannel());
        preset.put("device", getPresetMonitor().getDevice());
        preset.put("format", getPresetMonitor().getFormat());
        preset.put("linkedMonitors", getPresetMonitor().getLinkedMonitors());
        return preset;
    }

    @Bean
    private static Monitor getPresetMonitor() {
        Monitor monitor = new Monitor();
        monitor.setServerId(0);
        monitor.setName("Monitor Test");
        monitor.setEnabled(1);
        monitor.setChannel(0);
        monitor.setDevice("/dev/video0");
        monitor.setFormat(0);
        monitor.setLinkedMonitors("");
        return monitor;
    }
}
