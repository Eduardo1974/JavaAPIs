package com.example.demo.thread2.servidor;

import java.io.PrintStream;
import java.util.concurrent.*;

/**
 * Created by eduardo on 20/11/17.
 */
public class ProcessaResultado implements Callable<Void> {

    private Future<String> futureBD;
    private Future<String> futureWS;
    private PrintStream saidaCliente;

    public ProcessaResultado(Future<String> futureBD, Future<String> futureWS, PrintStream saidaCliente) {
        this.futureBD = futureBD;
        this.futureWS = futureWS;
        this.saidaCliente = saidaCliente;
    }

    @Override
    public Void call() {

        System.out.println("Processando resultados");
        try {
            String numeroMagico = this.futureBD.get(4, TimeUnit.SECONDS); //caso o retorno demore mais de 15 segundos sera lancada uma excecao de timeout
            String numeroMagico2 = this.futureWS.get(4, TimeUnit.SECONDS);

            saidaCliente.println("resultado "+ numeroMagico + " " + numeroMagico2);
            System.out.println("fim do processamento");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            System.out.println("cancelando processamento: timeout");
            saidaCliente.println("Timeout no processamento"); //avisando cliente do erro;
            this.futureBD.cancel(true);
            this.futureWS.cancel(true);
        }
        return null;
    }
}
