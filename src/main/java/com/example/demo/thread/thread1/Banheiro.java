package com.example.demo.thread.thread1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eduardo on 16/11/17.
 */
public class Banheiro {

    //Lock faz o mesmo papel do synchronized
    private Lock lock = new ReentrantLock();

    private boolean ehSujo = true;

    public void numero1() {

        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        synchronized(this) {
            System.out.println(nome + " entrando no banheiro");
            
            while(ehSujo) {
                esperaLaFora(nome);
            }
            System.out.println(nome + " fazendo coisa rapida");
            esperarUmPouco(1000);
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando as maos");
            System.out.println(nome + " saindo do banheiro");

        }
        ehSujo = true;

    }

    public void numero2() {

        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        synchronized(this) {
            System.out.println(nome + " entrando no banheiro");

            while(ehSujo) {
                esperaLaFora(nome);
            }
            System.out.println(nome + " fazendo coisa demorada");
            esperarUmPouco(10000);
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando as maos");
            System.out.println(nome + " saindo do banheiro");
        }
        ehSujo = true;
    }

    public void numero3() {

        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        lock.lock();
            System.out.println(nome + " entrando no banheiro");
            System.out.println(nome + " fazendo coisa rapida");
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando as maos");
            System.out.println(nome + " saindo do banheiro");
        lock.unlock();

    }

    public void numero4() {

        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        lock.lock();
            System.out.println(nome + " entrando no banheiro");
            System.out.println(nome + " fazendo coisa demorada");

             esperarUmPouco(10000);

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando as maos");
            System.out.println(nome + " saindo do banheiro");
        lock.unlock();
    }

    public void limpaBanheiro() {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        synchronized(this) {
            System.out.println("Limpando banheiro");
            if(!ehSujo) {
                System.out.println(nome + " banheiro limpo, vou sair");
                return;
            }

            ehSujo = false;

            this.notifyAll(); //ira avisar a todas as threads paradas para tentar executar novamente
            System.out.println("Banheiro limpo");
        }
    }

    public void esperarUmPouco(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void esperaLaFora(String nome) {
        System.out.println(nome + " eca, banheiro esta sujo");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Principal {
    public static void main(String[] args) {

        Banheiro banheiro = new Banheiro();
        Thread convidado1 = new Thread(new Tarefa1(banheiro), "Joao");
        Thread convidado2 = new Thread(new Tarefa2(banheiro), "Pedro");
        Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");
        //Thread convidado3 = new Thread(new Tarefa3(banheiro), "Ana");  //CONVIDADO COM LOCK
        //Thread convidado4 = new Thread(new Tarefa4(banheiro), "Maria");//CONVIDADO COM LOCK
        convidado1.start();
        convidado2.start();

        /**
         * Threads daemon são como prestadores de serviços para outras threads.
         * Elas são usadas para dar apoio à tarefas e só são necessárias rodar
         * quando as threads "normais" ainda estão sendo executadas. Uma thread1
         * daemon não impede a JVM de terminar desde que não existem mais threads
         * principais em execução. Um exemplo de uma thread1 daemon é o coletor de
         * lixo da JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
         */
        limpeza.setDaemon(true); //vai trabalhar enquanto houver threads aparalelas
        limpeza.start();

        //convidado3.start();
        //convidado4.start();

        /*
        SAÍDA
        Pedro entrando no banheiro
        Pedro fazendo coisa demorada
        Joao entrando no banheiro
        Joao fazendo coisa rapida
        Joao dando descarga
        Joao lavando as maos
        Joao saindo do banheiro
        Pedro dando descarga
        Pedro lavando as maos
        Pedro saindo do banheiro
        */

        // se os metodos nao estiverem sincronizados, as duas threads vao acessar o banheiro ao mesmo tempo
    }
}

class Tarefa1 implements Runnable {

    private Banheiro banheiro;

    public Tarefa1(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        banheiro.numero1();
    }
}

class Tarefa2 implements Runnable {

    private Banheiro banheiro;

    public Tarefa2(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        banheiro.numero2();
    }
}

class Tarefa3 implements Runnable {

    private Banheiro banheiro;

    public Tarefa3(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        banheiro.numero3();
    }
}

class Tarefa4 implements Runnable {

    private Banheiro banheiro;

    public Tarefa4(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        banheiro.numero4();
    }
}

class TarefaLimpeza implements Runnable {

    private Banheiro banheiro;

    public TarefaLimpeza(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        while (true) {
            banheiro.limpaBanheiro();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}