package com.example.demo.collections.fila;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by eduardo on 20/11/17.
 */
public class TesteFila {

    public static void main(String[] args) throws InterruptedException {

        Queue<String> fila = new LinkedList<>();
        fila.offer("c1");
        fila.offer("c2");
        fila.offer("c3");

        System.out.println(fila.poll()); //devolve e tira da fila
        System.out.println(fila.size());

        System.out.println(fila.peek()); //devolve mas nao tira da fila
        System.out.println(fila.size());

        //BlockingQueue é uma fila sincronizada FILA
        //Caso a lista fique vazia a thread ficara bloqueada ate que alguem insira um novo elemento
        BlockingQueue<String> fila2 = new ArrayBlockingQueue<String>(5);
        fila2.offer("c1");
        fila2.offer("c2");
        fila2.put("c3"); //utilizando o put caso seja ultrapassado o limite da fila, a thread ficara em espera ate que alguem saia da fila

        System.out.println(fila2.take()); //devolve e tira da fila
        System.out.println(fila2.take());
        System.out.println(fila2.take());
        System.out.println(fila2.take()); //enquanto nao for inserido um 4º elemento na fila a thread ira permanecer em espera aqui
        System.out.println(fila2.size());

        //BlockingDeque é uma fila sincronizada PILHA
        //Caso a lista fique vazia a thread ficara bloqueada ate que alguem insira um novo elemento
        BlockingDeque<String> fila3 = new LinkedBlockingDeque<>(5);
        fila3.offer("c1");
        fila3.offer("c2");
        fila3.offer("c3");

        System.out.println(fila3.takeLast()); //devolve e tira da fila
        System.out.println(fila3.takeLast());
        System.out.println(fila3.takeLast());
        System.out.println(fila3.takeLast());
        System.out.println(fila3.takeLast());
    }
}
