package com.example.demo.thread.thread1;

import java.util.*;

/**
 * Created by eduardo on 16/11/17.
 */
public class ListaJavaUtil {

    //ArrayList NAO IMPLEMENTA synchronized
    //private List<String> elementos = new ArrayList<>();

    //é possivel sincronizar uma lista utilizando o metodo synchronizedList
    //private List<String> elementos = Collections.synchronizedList(new ArrayList<>());

    //A lista Vector ja é sincronizada
    private List<String> elementos = new Vector<>();

    public synchronized void adicionaElementos(String elemento) {
        this.elementos.add(elemento);
    }

    public int tamanho() {
        return this.elementos.size();
    }

    public String pegaElemento(int posicao) {
        return this.elementos.get(posicao);
    }
}

class PrincipalListaUtil {

    public static void main(String[] args) throws InterruptedException {

        /*
        Na execucao caso o metodo adicionaElementos nao seja  synchronized
        as threads utilizaram o mesmo indice para escrever na listaManual, e incrementaram
        o indice mais de uma vez, fazendo com que posicoes sejam puladas.
        O problema é que precisamos garantir que  ttodo o código abaixo
        será executado de maneiro atômica, de uma vez só. Uma thread1 não
        pode parar na primeira linha, sem ter incrementada o índice.
        Se isso acontece, uma outra thread1 poderá executar esse código
        e adicionar um elemento na mesma posição.
         */

        /**
         * thread1-safe
         * Para os mapas (Map) podemos usar a antiga classe Hashtable:
            Map mapaThreadSafe = new Hashtable();
            ou Map mapaThreadSafe = new ConcurrentHashMap(); (performática)

           Para listas = Vector
           Para o Set = Set conjunto = Collections.synchronizedSet(new HashSet());
         */

        ListaJavaUtil listaJavaUtil = new ListaJavaUtil();
        for(int i= 0; i < 1000; i++) {
            new Thread(new TarefaAddElementoList(listaJavaUtil, i)).start();
        }

        Thread.sleep(2000);

        for(int i = 0; i < listaJavaUtil.tamanho(); i++) {
            System.out.println(i + " " + listaJavaUtil.pegaElemento(i));
        }
    }
}

class TarefaAddElementoList implements Runnable {

    private ListaJavaUtil elementos;
    private int numeroThread;

    public TarefaAddElementoList(ListaJavaUtil elementos, int numeroThread) {
        this.elementos = elementos;
        this.numeroThread = numeroThread;
    }

    @Override
    public void run() {
        for(int i= 0; i < 10; i++) {
            elementos.adicionaElementos("Thread: "+numeroThread + " elemento: " + i);
        }
    }
}
