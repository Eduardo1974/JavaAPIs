package com.example.demo.thread.thread2.servidor;

import java.io.PrintStream;

/**
 * Created by eduardo on 20/11/17.
 */
class ComandoC1 implements Runnable {

    private PrintStream saidaCliente; //o Pool de conexões passado tem um limite de 2
                                      // quando um terceiro cliente enviar um comando
                                      // e o pool estiver ocupado, ele ira aguardar o
                                      //termino de alguma das threads em execucao

    public ComandoC1(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public void run() {
        System.out.println("Executando comando c1");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saidaCliente.println("comando recebido: processando"); //enviando resposta ao cliente
    }
}
