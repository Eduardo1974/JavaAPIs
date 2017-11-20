package com.example.demo.thread2.servidor;

import java.util.concurrent.ThreadFactory;

/**
 * Created by eduardo on 20/11/17.
 */
public class FabricaDeThread implements ThreadFactory {

    private static int numero = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "Servidor de tarefas " + numero);
        numero++;
        thread.setUncaughtExceptionHandler(new TratadorDeExeccao()); //quando ocorre uma exececao
        // nessa pilha de execucao iremos especificar oque a exececao ira fazer
        return thread;
    }
}
