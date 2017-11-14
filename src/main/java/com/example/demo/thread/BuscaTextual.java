package com.example.demo.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by eduardo on 14/11/17.
 */
public class BuscaTextual {

    public static void main(String[] args) {

        String nome =  "ek";
        String path = "/home/eduardo/Documentos/edu/examplos/GitHubEduardo/JavaAPIs/src/main/resources";

        Thread assinatura1 = new Thread(new TarefaBuscaTextual("/assinaturas1.txt", nome, path + "/assinaturas1.txt"), "assinatura1 textual");
        Thread assinatura2 = new Thread(new TarefaBuscaTextual("/assinaturas2.txt", nome, path + "/assinaturas2.txt"), "assinatura2 textual");
        Thread autores = new Thread(new TarefaBuscaTextual("/autores.txt", nome, path + "/autores.txt"), "autores textual");
        assinatura1.start();
        assinatura2.start();
        autores.start();
    }
}


class TarefaBuscaTextual implements Runnable {

    private String nomeArquivo;
    private String nome;
    private String path;

    public TarefaBuscaTextual(String nomeArquivo, String nome, String path) {
        this.nomeArquivo = nomeArquivo;
        this.nome = nome;
        this.path = path;
    }

    @Override
    public void run() {

        try {
            Scanner scanner = new Scanner(new File(path));
            int numero = 1;
            while (scanner.hasNextLine()) {
                String nome = scanner.nextLine();
                if(nome.contains(this.nome)) {
                    System.out.println("arquivo: " + nomeArquivo + " - " + numero + " - " + nome);
                }
                numero ++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}