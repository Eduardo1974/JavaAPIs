package com.example.demo.thread2.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by eduardo on 16/11/17.
 */
public class ClienteTarefas {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 12345);

        System.out.println("Cliente conectado");

        Scanner teclado = new Scanner(System.in);
        teclado.nextLine();

        socket.close();
    }
}
