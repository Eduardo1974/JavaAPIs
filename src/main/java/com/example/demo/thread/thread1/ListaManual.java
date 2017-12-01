package com.example.demo.thread.thread1;

/**
 * Created by eduardo on 16/11/17.
 */
public class ListaManual {

    private String[] elementos = new String[100];
    private int indice = 0;

    public synchronized void adicionaElementos(String elemento) {
        this.elementos[indice] = elemento;
        this.indice ++;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(this.indice == this.elementos.length) {
            System.out.println("lista cheia, notificando");
            this.notify(); //quando a lista for completada a thread1 de imprimir será acordada
        }
    }

    public int tamanho() {
        return this.elementos.length;
    }

    public String pegaElemento(int posicao) {
        return this.elementos[posicao];
    }
}

class PrincipalLista {

    public static void main(String[] args) throws InterruptedException {

        /*
        Na execucao caso o metodo adicionaElementos nao seja  synchronized
        as threads utilizaram o mesmo indice para escrever na listaManual, e incrementaram
        o indice mais de uma vez, fazendo com que posicoes sejam puladas.
         */
        ListaManual listaManual = new ListaManual();
        for(int i= 0; i < 10; i++) {
            new Thread(new TarefaAddElemento(listaManual, i)).start();
        }

        Thread imprimir = new Thread(new TarefaImprimir(listaManual));
        imprimir.start();

    }
}

class TarefaImprimir implements Runnable {
    ListaManual listaManual;

    public TarefaImprimir(ListaManual listaManual) {
        this.listaManual = listaManual;
    }

    @Override
    public void run() {

        synchronized (listaManual) {
            try {
                System.out.println("indo dormir, aguardando notificacao");
                listaManual.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < listaManual.tamanho(); i++) {
                System.out.println(i + " " + listaManual.pegaElemento(i));
            }
        }
    }
}

class TarefaAddElemento implements Runnable {

    private ListaManual elementos;
    private int numeroThread;

    public TarefaAddElemento(ListaManual elementos, int numeroThread) {
        this.elementos = elementos;
        this.numeroThread = numeroThread;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            elementos.adicionaElementos("Thread: "+numeroThread + " elemento: " + i);
        }
    }
}
