package com.example.demo.thread2.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by eduardo on 20/11/17.
 */
class DistribuirTarefas implements Runnable {

    private BlockingQueue<String> filaComandos;
    private ExecutorService executorService;
    private Socket socket;
    private ServidorTarefas servidorTarefas;

    public DistribuirTarefas(BlockingQueue<String> filaComandos, ExecutorService executorService, Socket socket, ServidorTarefas servidorTarefas) {
        this.filaComandos = filaComandos;
        this.executorService = executorService;
        this.socket = socket;
        this.servidorTarefas = servidorTarefas;
    }

    @Override
    public void run() {

        System.out.println("Distribuindo tarefas para: " + socket);

        try {
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();
                switch (comando) {
                    case "c1":
                        ComandoC1 comandoC1 = new ComandoC1(saidaCliente);
                        this.executorService.execute(comandoC1);
                        break;
                    case "c2":
                        ComandoC2WS comandoC2WS = new ComandoC2WS(saidaCliente);
                        ComandoC2BD comandoC2BD = new ComandoC2BD(saidaCliente);
                        Future<String> futureWS = this.executorService.submit(comandoC2WS); //recebe retorno da thread
                        Future<String> futureBD = this.executorService.submit(comandoC2BD);

                        this.executorService.submit(new ProcessaResultado(futureBD, futureWS, saidaCliente));
                        break;
                    case "c3":
                        this.filaComandos.put(comando);
                        saidaCliente.println("Adicionado C3");
                        break;
                    case "fim":
                        servidorTarefas.parar();
                        break;
                    default: {
                        saidaCliente.println("Comando não encontrado");
                        break;
                    }
                }
                System.out.println(comando);
            }
            entradaCliente.close();
            saidaCliente.close();
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
