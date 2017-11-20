package com.example.demo.thread2.servidor;

/**
 * Created by eduardo on 20/11/17.
 */
public class TratadorDeExeccao implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Deu erro na Thread "+t.getName() + " " + e.getMessage());
    }
}
