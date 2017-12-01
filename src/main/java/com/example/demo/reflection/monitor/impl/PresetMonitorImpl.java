package com.example.demo.reflection.monitor.impl;

import com.example.demo.reflection.monitor.Monitor;
import com.example.demo.reflection.monitor.PresetMonitorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * A classe PresetMonitorImpl disponibiliza um preset com as informacoes padroes de monitor.
 * @author Eduardo Alves
 * @version 1.0
 */
@Component
public class PresetMonitorImpl implements PresetMonitorConfiguration {

    /**
     * O metodo e reponsavel por retornar um Map com as configuracoes
     * padroes de monitor. Todos os campos presentes no mapa são
     * obrigatorios no banco de dados, e caso o usuario nao informe
     * algum, o servico responsavel devera obter a informação por esse metodo
     * @return Map monitor
     */
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

    /**
     * O metodo retorna uma instancia de monitor com os campos obrigatórios no banco de dados
     * @return monitor
     */
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
