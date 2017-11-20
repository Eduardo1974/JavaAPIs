package com.example.demo.thread2.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by eduardo on 20/11/17.
 */
class ComandoC2WS implements Callable<String> {

    private PrintStream saidaCliente; //o Pool de conexões passado tem um limite de 2
    // quando um terceiro cliente enviar um comando
    // e o pool estiver ocupado, ele ira aguardar o
    //termino de alguma das threads em execucao

    public ComandoC2WS(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.println("Executando comando c2 WS");
        saidaCliente.println("comando recebido: processando"); //enviando resposta ao cliente
        Thread.sleep(20000);
        System.out.println("Comando c2 finalizado WS");
        return Integer.toString(new Random().nextInt(100));
    }
}
