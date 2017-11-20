package com.example.demo.thread2.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by eduardo on 16/11/17.
 */
public class ClienteTarefas {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 12345);

        System.out.println("Cliente conectado");

        Thread enviaComando = new Thread(() -> {
            PrintStream printStream = null;
            try {
                System.out.println("enviando comandos");
                printStream = new PrintStream(socket.getOutputStream());
                Scanner teclado = new Scanner(System.in);
                while (teclado.hasNextLine()) {
                    String linha = teclado.nextLine();

                    if(linha.trim().equals("")) {
                        break;
                    }

                    printStream.println(linha);
                }
                printStream.close();
                teclado.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread recebeResposta = new Thread(() -> {
            Scanner respostaServidor = null;
            try {
                System.out.println("recebendo dados servidor");
                respostaServidor = new Scanner(socket.getInputStream());
                while (respostaServidor.hasNextLine()) {
                    String linha = respostaServidor.nextLine();
                    System.out.println(linha);
                }
                respostaServidor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        enviaComando.start();
        recebeResposta.start();

        try {
            enviaComando.join(); //estou dizendo que a outras thread terao q esperar essa thread terminar

            //thread.join(30000);
            //Isso significa que vamos esperar 30s para se "juntar"
            // a outra thread. Depois dos 30s continuaremos, mesmo
            // se a outra thread não tiver finalizado ainda.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("fechando socket cliente");
        socket.close();
    }
}
