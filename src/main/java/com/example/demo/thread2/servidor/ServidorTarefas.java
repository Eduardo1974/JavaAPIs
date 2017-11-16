package com.example.demo.thread2.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eduardo on 16/11/17.
 */
public class ServidorTarefas {

    public static void main(String[] args) throws IOException {

        System.out.println("servidor estabelecido");
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Aceitando novo cliente porta: " + socket.getPort());

            DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);
            Thread clienteThread = new Thread(distribuirTarefas);
            clienteThread.start();
        }
    }
}

class DistribuirTarefas implements Runnable {

    private Socket socket;

    public DistribuirTarefas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Distribuindo tarefas para: "+socket);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}