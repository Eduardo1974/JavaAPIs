package com.example.demo.thread2.servidor;

import java.io.IOException;

/**
 * Created by eduardo on 20/11/17.
 */
public class PrincipalThread {

    public static void main(String[] args) throws IOException {
        ServidorTarefas servidorTarefas = new ServidorTarefas();
        servidorTarefas.rodar();
        servidorTarefas.parar();
    }
}
