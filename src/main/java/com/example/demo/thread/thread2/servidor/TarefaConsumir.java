package com.example.demo.thread.thread2.servidor;

import java.util.concurrent.BlockingQueue;

/**
 * Created by eduardo on 20/11/17.
 */
public class TarefaConsumir implements Runnable {

    private BlockingQueue<String> filaComandos;

    public TarefaConsumir(BlockingQueue<String> filaComandos) {
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {
        try {
            String comando = null;
            while ((comando = filaComandos.take()) != null ) { //enquanto houver comandos ele ira executar
                System.out.println("Consumindo comando " + comando);
                Thread.sleep(5000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
