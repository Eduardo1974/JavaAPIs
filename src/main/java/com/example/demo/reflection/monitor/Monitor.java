package com.example.demo.reflection.monitor;

import lombok.Data;

import java.io.Serializable;

/**
 * A classe Monitor representa o modelo de dados de cameras no banco zm.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
public class Monitor implements Serializable {

    protected Integer id;

    protected String name;

    protected Integer serverId;

    protected Integer enabled;

    protected String linkedMonitors;

    protected String device;

    protected Integer channel;

    protected Integer format;

    protected Integer v4LMultiBuffer;
}
