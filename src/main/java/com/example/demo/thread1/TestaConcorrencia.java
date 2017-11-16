package com.example.demo.thread1;

/**
 * Created by eduardo on 14/11/17.
 */
public class TestaConcorrencia {

    public static void main(String[] args) {

        BarraDeProgresso barraDeProgresso = new BarraDeProgresso();
        Thread th1 = new Thread(barraDeProgresso);
        th1.start();

        CopiadorDeArquivos copiadorDeArquivos = new CopiadorDeArquivos();
        Thread th2 = new Thread(copiadorDeArquivos);
        th2.start();
    }
}

class BarraDeProgresso implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("barra de progresso");
        }
    }
}

class CopiadorDeArquivos implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Copiador de Arquivos");
        }
    }
}